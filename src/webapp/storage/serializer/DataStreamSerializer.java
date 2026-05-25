package webapp.storage.serializer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import webapp.model.AbstractSection;
import webapp.model.Company;
import webapp.model.CompanySection;
import webapp.model.ContactType;
import webapp.model.Link;
import webapp.model.ListSection;
import webapp.model.Resume;
import webapp.model.SectionType;
import webapp.model.TextSection;
import webapp.util.DateUtil;

public class DataStreamSerializer implements Serializer {
    private static final String NULL_VALUE = "null";

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, Link> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            writeContacts(contacts, dos);
            writeSections(r, dos);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readContacts(dis, resume);

            int sections = dis.readInt();
            for (int i = 0; i < sections; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                int sectionSize = dis.readInt();
                AbstractSection section = switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> readText(dis);
                    case ACHIEVEMENT, QUALIFICATIONS -> readLines(dis, sectionSize);
                    case EXPERIENCE, EDUCATION -> readCompanies(dis, sectionSize);
                };
                resume.setSection(sectionType, section);
            }
            return resume;
        }
    }

    private void writeContacts(Map<ContactType, Link> contacts, DataOutputStream dos) throws IOException {
        for (Map.Entry<ContactType, Link> entry : contacts.entrySet()) {
            dos.writeUTF(entry.getKey().name());
            writeLink(entry.getValue(), dos);
        }
    }

    private void writeSections(Resume r, DataOutputStream dos) throws IOException {
        Map<SectionType, AbstractSection> sections = r.getSections();
        dos.writeInt(sections.size());
        for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
            String sectionType = entry.getKey().name();
            dos.writeUTF(sectionType);
            AbstractSection section = entry.getValue();
            switch (entry.getKey()) {
                case PERSONAL, OBJECTIVE -> writeText((TextSection) section, dos);
                case ACHIEVEMENT, QUALIFICATIONS -> writeLines((ListSection) section, dos);
                case EXPERIENCE, EDUCATION -> writeCompanies((CompanySection) section, dos);
                default -> throw new IllegalStateException("Error: add case " + section +
                        " in switch expression");
            }
        }
    }

    private void writeText(TextSection textSection, DataOutputStream dos) throws IOException {
        dos.writeInt(1);
        dos.writeUTF(textSection.getText());
    }

    private void writeLines(ListSection listSection, DataOutputStream dos) throws IOException {
        dos.writeInt(listSection.getLines().size());
        for (String line : listSection.getLines()) {
            dos.writeUTF(line);
        }
    }

    private void writeCompanies(CompanySection companySection, DataOutputStream dos) throws IOException {
        dos.writeInt(companySection.getCompanies().size());
        for (Company company : companySection.getCompanies()) {
            writeLink(company.getLink(), dos);
            dos.writeInt(company.getPositions().size());
            writePosition(company, dos);
        }
    }

    private void writeLink(Link link, DataOutputStream dos) throws IOException {
        dos.writeUTF(link.getName());
        URI url = link.getUrl();
        dos.writeUTF(asString(url));
    }

    private void writePosition(Company company, DataOutputStream dos) throws IOException {
        for (Company.Position position : company.getPositions()) {
            dos.writeInt(position.getStartDate().getYear());
            dos.writeInt(position.getStartDate().getMonthValue());
            dos.writeInt(position.getStopDate().getYear());
            dos.writeInt(position.getStopDate().getMonthValue());
            dos.writeUTF(position.getTitle());
            dos.writeUTF(position.getDescription() == null ? NULL_VALUE : position.getDescription());
        }
    }

    private void readContacts(DataInputStream dis, Resume resume) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            resume.setContact(ContactType.valueOf(dis.readUTF()), readLink(dis));
        }
    }

    private TextSection readText(DataInputStream dis) throws IOException {
        return new TextSection(dis.readUTF());
    }

    private ListSection readLines(DataInputStream dis, int sectionSize) throws IOException {
        List<String> lines = new ArrayList<>(sectionSize);
        for (int i = 0; i < sectionSize; i++) {
            lines.add(dis.readUTF());
        }
        return new ListSection(lines);
    }

    private CompanySection readCompanies(DataInputStream dis, int sectionSize)
            throws IOException {
        List<Company> companies = new ArrayList<>(sectionSize);
        for (int i = 0; i < sectionSize; i++) {
            companies.add(new Company(readLink(dis), readPositions(dis)));
        }
        return new CompanySection(companies);
    }

    private Link readLink(DataInputStream dis) throws IOException {
        return new Link(dis.readUTF(), asUrl(dis.readUTF()));
    }

    private List<Company.Position> readPositions(DataInputStream dis) throws IOException {
        int quantity = dis.readInt();
        List<Company.Position> positions = new ArrayList<>(quantity);
        for (int i = 0; i < quantity; i++) {
            LocalDate startDate = DateUtil.of(dis.readInt(), dis.readInt());
            LocalDate stopDate = DateUtil.of(dis.readInt(), dis.readInt());
            String title = dis.readUTF();
            String description = dis.readUTF();
            description = description.equals(NULL_VALUE) ? null : description;
            positions.add(new Company.Position(startDate, stopDate, title, description));
        }
        return positions;
    }

    private URI asUrl(String url) {
        return url.equals(NULL_VALUE) ? null : URI.create(url);
    }

    private String asString(URI url) {
        return url == null ? NULL_VALUE : url.toString();
    }
}