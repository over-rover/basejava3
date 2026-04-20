package webapp;

import java.net.URI;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import webapp.model.Company;
import webapp.model.CompanySection;
import webapp.model.ContactType;
import webapp.model.Link;
import webapp.model.ListSection;
import webapp.model.Resume;
import webapp.model.SectionType;
import webapp.model.TextSection;

public class ResumeTestData {
    static void main() {
        createResume("uuid4", "Григорий Кислин");
    }

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

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
        Link link = new Link("Java Online Projects");
        Company.Position position = new Company.Position(2013, Month.OCTOBER,
                "Автор проекта.",
                "Создание, организация и проведение Java онлайн проектов и стажировок.");
        List<Company.Position> positions = new ArrayList<>();
        positions.add(position);
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(link, positions));
        CompanySection companiesSection = new CompanySection(companies);
        resume.setSection(SectionType.EXPERIENCE, companiesSection);

        link = new Link("Wrike", URI.create("https://wrike.dns-name"));
        position = new Company.Position(2014, Month.OCTOBER, 2016, Month.JANUARY,
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        positions = new ArrayList<>();
        positions.add(position);
        companies.add(new Company(link, positions));
        resume.setSection(SectionType.EXPERIENCE, companiesSection);

        link = new Link("RIT Center", URI.create("https://rit-center.dns-name"));
        position = new Company.Position(2012, Month.APRIL, 2014, Month.OCTOBER,
                "Java архитектор",
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы.");
        positions = new ArrayList<>();
        positions.add(position);
        companies.add(new Company(link, positions));
        resume.setSection(SectionType.EXPERIENCE, companiesSection);

        // fill EDUCATION
        link = new Link("Coursera", URI.create("https://coursera.dns-name"));
        position = new Company.Position(2013, Month.MARCH, 2013, Month.MAY,
                "Functional Programming Principles in Scala' by Martin Odersky");
        positions = new ArrayList<>();
        positions.add(position);
        companies = new ArrayList<>();
        companies.add(new Company(link, positions));
        companiesSection = new CompanySection(companies);
        resume.setSection(SectionType.EDUCATION, companiesSection);

        link = new Link("Luxoft", URI.create("https://luxoft.dns-name"));
        position = new Company.Position(2011, Month.MARCH, 2011, Month.APRIL,
                "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.");
        positions = new ArrayList<>();
        positions.add(position);
        companies.add(new Company(link, positions));
        resume.setSection(SectionType.EDUCATION, companiesSection);

        link = new Link("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                URI.create("https://spb.university.dns-name"));
        position = new Company.Position(1993, Month.SEPTEMBER,
                1996, Month.JULY,
                "Аспирантура (программист С, С++)");
        positions = new ArrayList<>();
        positions.add(position);
        position = new Company.Position(1987, Month.SEPTEMBER,
                1993, Month.JULY,
                "Инженер (программист Fortran, C)");
        positions.add(position);
        companies.add(new Company(link, positions));
        resume.setSection(SectionType.EDUCATION, companiesSection);

        System.out.println(resume);
        return resume;
    }
}