import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import webapp.model.*;

public class ResumeTestData {
    static void main() {
        Resume resume = new Resume("uuid4", "Григорий Кислин");

        //fill Contacts
        List<Contact> contacts = new ArrayList<>();
        Contact contact = new Contact();
        contact.setContact("+7(921) 855-0482");
        contacts.add(contact);
        Section<List<Contact>> contactListSection = new ContactListSection();
        contactListSection.setSectionContent(contacts);
        resume.setContacts(ContactType.TEL, contactListSection);

        contact = new Contact();
        contact.setContact("grigory.kislin");
        ContactSection contactSection = new ContactSection();
        contactSection.setSectionContent(contact);
        resume.setContacts(ContactType.SKYPE, contactSection);

        contact = new Contact();
        contact.setContact("gkislin@yandex.ru");
        contacts = new ArrayList<>();
        contacts.add(contact);
        contact = new Contact();
        contact.setContact("gkislin@mail.ru");
        contacts.add(contact);
        contactListSection = new ContactListSection();
        contactListSection.setSectionContent(contacts);
        resume.setContacts(ContactType.EMAIL, contactListSection);

        // fill OBJECTIVE
        Section<String> textSection = new TextSection();
        String stringContent = "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям";
        textSection.setSectionContent(stringContent);
        resume.setSection(SectionType.OBJECTIVE, textSection);

        // fill PERSONAL
        textSection = new TextSection();
        stringContent = "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.";
        textSection.setSectionContent(stringContent);
        resume.setSection(SectionType.PERSONAL, textSection);

        // fill ACHIEVEMENTS
        List<String> listContent = new ArrayList<>();
        stringContent = "Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет";
        listContent.add(stringContent);

        stringContent = "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников";
        listContent.add(stringContent);

        stringContent = "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.";
        listContent.add(stringContent);

        Section<List<String>> listSection = new ListSection();
        listSection.setSectionContent(listContent);
        resume.setSection(SectionType.ACHIEVEMENT, listSection);

        // fill QUALIFICATIONS
        listContent = new ArrayList<>();
        stringContent = "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2";
        listContent.add(stringContent);
        stringContent = "Version control: Subversion, Git, Mercury, ClearCase, Perforce";
        listContent.add(stringContent);
        stringContent = "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB";
        listContent.add(stringContent);
        stringContent = "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy";
        listContent.add(stringContent);

        listSection = new ListSection();
        listSection.setSectionContent(listContent);
        resume.setSection(SectionType.QUALIFICATIONS, listSection);

        // fill EXPERIENCE
        Section<List<Company>> companySection = new CompanyListSection();
        List<Company> companies = new ArrayList<>();
        companySection.setSectionContent(companies);

        Company company = new Company();
        company.setCompanyName("Java Online Projects");
        company.setStartDate(LocalDate.of(2013, 10, 1));
        company.setStopDate(LocalDate.now());
        company.setPosition("Автор проекта.");
        stringContent = "Создание, организация и проведение Java онлайн проектов и стажировок.";
        company.setDuty(stringContent);
        companies.add(company);
        resume.setSection(SectionType.EXPERIENCE, companySection);

        company = new Company();
        company.setCompanyName("Wrike");
        company.setStartDate(LocalDate.of(2014, 10, 1));
        company.setStopDate(LocalDate.of(2016, 1, 1));
        company.setPosition("Старший разработчик (backend)");
        stringContent = "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.";
        company.setDuty(stringContent);
        companies.add(company);
        resume.setSection(SectionType.EXPERIENCE, companySection);

        company = new Company();
        company.setCompanyName("RIT Center");
        company.setStartDate(LocalDate.of(2012, 4, 1));
        company.setStopDate(LocalDate.of(2014, 10, 1));
        company.setPosition("Java архитектор");
        stringContent = "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы.";
        company.setDuty(stringContent);
        companies.add(company);
        resume.setSection(SectionType.EXPERIENCE, companySection);

        // fill EDUCATION
        Section<List<Company>> eduCompanySection = new CompanyListSection();
        List<Company> eduCompanies = new ArrayList<>();
        eduCompanySection.setSectionContent(eduCompanies);

        Company eduCompany = new Company();
        eduCompany.setCompanyName("Coursera");
        eduCompany.setStartDate(LocalDate.of(2013, 3, 1));
        eduCompany.setStopDate(LocalDate.of(2013, 5, 1));
        stringContent = "Functional Programming Principles in Scala' by Martin Odersky";
        eduCompany.setDuty(stringContent);
        eduCompanies.add(eduCompany);
        resume.setSection(SectionType.EDUCATION, eduCompanySection);

        eduCompany = new Company();
        eduCompany.setCompanyName("Luxoft");
        eduCompany.setStartDate(LocalDate.of(2011, 3, 1));
        eduCompany.setStopDate(LocalDate.of(2011, 4, 1));
        stringContent = "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.";
        eduCompany.setDuty(stringContent);
        eduCompanies.add(eduCompany);
        resume.setSection(SectionType.EDUCATION, eduCompanySection);

        System.out.println(resume);
    }
}
