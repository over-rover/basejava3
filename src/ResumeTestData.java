import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import webapp.model.Company;
import webapp.model.CompanySection;
import webapp.model.ContactType;
import webapp.model.Link;
import webapp.model.ListSection;
import webapp.model.Period;
import webapp.model.Resume;
import webapp.model.SectionType;
import webapp.model.TextSection;

public class ResumeTestData {
    static void main() {
        Resume resume = new Resume("uuid4", "Григорий Кислин");

        // fill Contacts
        Link contact = new Link("+7(921) 855-0482", URI.create("https://гиперссылка"));
        resume.setContact(ContactType.TEL, contact);

        contact = new Link("grigory.kislin", URI.create("https://skype.com"));
        resume.setContact(ContactType.SKYPE, contact);

        contact = new Link("gkislin@yandex.ru", URI.create("https://mail.yandex.ru"));
        resume.setContact(ContactType.EMAIL, contact);

        // fill OBJECTIVE
        TextSection text = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.setSection(SectionType.OBJECTIVE, text);

        // fill PERSONAL
        text = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.setSection(SectionType.PERSONAL, text);

        // fill ACHIEVEMENTS
        List<String> lines = new ArrayList<>();
        lines.add("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        lines.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников");
        lines.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        ListSection listSection = new ListSection(lines);
        resume.setSection(SectionType.ACHIEVEMENT, listSection);

        // fill QUALIFICATIONS
        lines = new ArrayList<>();
        lines.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        lines.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        lines.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        lines.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        listSection = new ListSection(lines);
        resume.setSection(SectionType.QUALIFICATIONS, listSection);

        // fill EXPERIENCE
        Link link = new Link("Java Online Projects", URI.create("https://JOP.dns-name"));
        Period period = new Period("Автор проекта.",
                "Создание, организация и проведение Java онлайн проектов и стажировок.",
                LocalDate.of(2013, 10, 1),
                LocalDate.now());
        List<Period> positions = new ArrayList<>();
        positions.add(period);
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(link, positions));
        CompanySection companiesSection = new CompanySection(companies);
        resume.setSection(SectionType.EXPERIENCE, companiesSection);

        link = new Link("Wrike", URI.create("https://wrike.dns-name"));
        period = new Period("Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.",
                LocalDate.of(2014, 10, 1),
                LocalDate.of(2016, 1, 1));
        positions = new ArrayList<>();
        positions.add(period);
        companies.add(new Company(link, positions));
        resume.setSection(SectionType.EXPERIENCE, companiesSection);

        link = new Link("RIT Center", URI.create("https://rit-center.dns-name"));
        period = new Period("Java архитектор",
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы.",
                LocalDate.of(2012, 4, 1),
                LocalDate.of(2014, 10, 1));
        positions = new ArrayList<>();
        positions.add(period);
        companies.add(new Company(link, positions));
        resume.setSection(SectionType.EXPERIENCE, companiesSection);

        // fill EDUCATION
        link = new Link("Coursera", URI.create("https://coursera.dns-name"));
        period = new Period("",
                "Functional Programming Principles in Scala' by Martin Odersky",
                LocalDate.of(2013, 3, 1),
                LocalDate.of(2013, 5, 1));
        positions = new ArrayList<>();
        positions.add(period);
        companies = new ArrayList<>();
        companies.add(new Company(link, positions));
        companiesSection = new CompanySection(companies);
        resume.setSection(SectionType.EDUCATION, companiesSection);

        link = new Link("Luxoft", URI.create("https://luxoft.dns-name"));
        period = new Period("",
                "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.",
                LocalDate.of(2011, 3, 1),
                LocalDate.of(2011, 4, 1));
        positions = new ArrayList<>();
        positions.add(period);
        companies.add(new Company(link, positions));
        resume.setSection(SectionType.EDUCATION, companiesSection);

        link = new Link("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                URI.create("https://spb.university.dns-name"));
        period = new Period("",
                "Аспирантура (программист С, С++)",
                LocalDate.of(1993, 9, 1),
                LocalDate.of(1996, 7, 1));
        positions = new ArrayList<>();
        positions.add(period);
        period = new Period("",
                "Инженер (программист Fortran, C)",
                LocalDate.of(1987, 9, 1),
                LocalDate.of(1993, 7, 1));
        positions.add(period);
        companies.add(new Company(link, positions));
        resume.setSection(SectionType.EDUCATION, companiesSection);

        System.out.println(resume);
    }
}
