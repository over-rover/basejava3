package webapp.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class Resume {
    private final String uuid;
    private final String fullName;
    private final Map<ContactType, Link> contacts = new LinkedHashMap<>();
    private final Map<SectionType, AbstractSection> sections = new LinkedHashMap<>();

    public Resume() {
        this(UUID.randomUUID().toString());
    }

    public Resume(String uuid) {
        this(uuid, "Default fullname");
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setContacts(ContactType contactType, Link link) {
        contacts.put(contactType, link);
    }

    public void setSection(SectionType sectionType, AbstractSection content) {
        sections.put(sectionType, content);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(fullName + "\n");
        mapToStringBuilder(contacts, sb);
        mapToStringBuilder(sections, sb);
        return sb.toString();
    }

    private void mapToStringBuilder(Map<?, ?> map, StringBuilder sb) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append(entry.getValue()).append("\n");
        }
    }
}