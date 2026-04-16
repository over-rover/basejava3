package webapp.model;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Resume implements Comparable<Resume> {
    private final String uuid;
    private final String fullName;
    private final Map<ContactType, Link> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setContact(ContactType contactType, Link link) {
        contacts.put(contactType, link);
    }

    public void setSection(SectionType sectionType, AbstractSection content) {
        sections.put(sectionType, content);
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
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