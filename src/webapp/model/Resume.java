package webapp.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class Resume {
    private final String uuid;
    private final String fullName;
    //изначально было Map<SectionType, Section> section - компилятор предупредил о raw type
    //попробовал Section<S> - cannot resolve symbol S. Не знает класс S
    //методом исключения поставил ?, друих вариантов не нашел.
    private final Map<ContactType, Section<?>> contacts = new LinkedHashMap<>();
    private final Map<SectionType, Section<?>> section = new LinkedHashMap<>();

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

    public void setContacts(ContactType contactType, Section<?> contact) {
        contacts.put(contactType, contact);
    }

    public void setSection(SectionType sectionType, Section<?> sectionContent) {
        section.put(sectionType, sectionContent);
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
        for (Map.Entry<ContactType, Section<?>> entry : contacts.entrySet()) {
            sb.append(entry.getKey());
            sb.append(entry.getValue());
        }

        for (Map.Entry<SectionType, Section<?>> entry : section.entrySet()) {
            sb.append(entry.getKey());
            sb.append(entry.getValue());
        }
        return sb.toString();
    }
}