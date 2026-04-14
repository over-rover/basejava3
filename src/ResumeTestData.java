import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import webapp.model.*;

public class ResumeTestData {
    static void main() {
        Resume resume = new Resume("uuid4", "Григорий Кислин");

        // fill Contacts
        Link contact = new Link();
        contact.setName("+7(921) 855-0482");
        contact.setHyperlink(URI.create("https://гиперссылка"));
        resume.setContacts(ContactType.TEL, contact);

        contact = new Link();
        contact.setName("grigory.kislin");
        contact.setHyperlink(URI.create("https://skype.com"));
        resume.setContacts(ContactType.SKYPE, contact);

        contact = new Link();
        contact.setName("gkislin@yandex.ru");
        contact.setHyperlink(URI.create("https://mail.yandex.ru"));
        resume.setContacts(ContactType.EMAIL, contact);

        // fill OBJECTIVE
        TextSection text = new TextSection();
        text.setText("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.setSection(SectionType.OBJECTIVE, text);

        // fill PERSONAL
        text = new TextSection();
        text.setText("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.setSection(SectionType.PERSONAL, text);

        // fill ACHIEVEMENTS
        List<String> strings = new ArrayList<>();
        strings.add("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        strings.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников");
        strings.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        ListSection listSection = new ListSection();
        listSection.setStrings(strings);
        resume.setSection(SectionType.ACHIEVEMENT, listSection);

        // fill QUALIFICATIONS
        strings = new ArrayList<>();
        strings.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        strings.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        strings.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        strings.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        listSection = new ListSection();
        listSection.setStrings(strings);
        resume.setSection(SectionType.QUALIFICATIONS, listSection);

        // fill EXPERIENCE
        Company company = new Company();
        Link link = new Link();
        link.setName("Java Online Projects");
        link.setHyperlink(URI.create("https://JOP.dns-name"));
        company.setLink(link);
        Period period = new Period();
        period.setStartDate(LocalDate.of(2013, 10, 1));
        period.setStopDate(LocalDate.now());
        period.setPosition("Автор проекта.");
        period.setDuty("Создание, организация и проведение Java онлайн проектов и стажировок.");
        List<Period> periods = new ArrayList<>();
        periods.add(period);
        company.setPeriods(periods);
        List<Company> companies = new ArrayList<>();
        companies.add(company);
        CompanySection companiesSection = new CompanySection();
        companiesSection.setCompanies(companies);
        resume.setSection(SectionType.EXPERIENCE, companiesSection);

        company = new Company();
        link = new Link();
        link.setName("Wrike");
        link.setHyperlink(URI.create("https://wrike.dns-name"));
        company.setLink(link);
        period = new Period();
        period.setStartDate(LocalDate.of(2014, 10, 1));
        period.setStopDate(LocalDate.of(2016, 1, 1));
        period.setPosition("Старший разработчик (backend)");
        period.setDuty("Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        periods = new ArrayList<>();
        periods.add(period);
        company.setPeriods(periods);
        companies.add(company);
        companiesSection.setCompanies(companies);
        resume.setSection(SectionType.EXPERIENCE, companiesSection);

        company = new Company();
        link = new Link();
        link.setName("RIT Center");
        link.setHyperlink(URI.create("https://rit-center.dns-name"));
        company.setLink(link);
        period = new Period();
        period.setStartDate(LocalDate.of(2012, 4, 1));
        period.setStopDate(LocalDate.of(2014, 10, 1));
        period.setPosition("Java архитектор");
        period.setDuty("Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы.");
        periods = new ArrayList<>();
        periods.add(period);
        company.setPeriods(periods);
        companies.add(company);
        companiesSection.setCompanies(companies);
        resume.setSection(SectionType.EXPERIENCE, companiesSection);

        // fill EDUCATION
        company = new Company();
        link = new Link();
        link.setName("Coursera");
        link.setHyperlink(URI.create("https://coursera.dns-name"));
        company.setLink(link);
        period = new Period();
        period.setStartDate(LocalDate.of(2013, 3, 1));
        period.setStopDate(LocalDate.of(2013, 5, 1));
        period.setDuty("Functional Programming Principles in Scala' by Martin Odersky");
        periods = new ArrayList<>();
        periods.add(period);
        company.setPeriods(periods);
        companies = new ArrayList<>();
        companies.add(company);
        companiesSection = new CompanySection();
        companiesSection.setCompanies(companies);
        resume.setSection(SectionType.EDUCATION, companiesSection);

        company = new Company();
        link = new Link();
        link.setName("Luxoft");
        link.setHyperlink(URI.create("https://luxoft.dns-name"));
        company.setLink(link);
        period = new Period();
        period.setStartDate(LocalDate.of(2011, 3, 1));
        period.setStopDate(LocalDate.of(2011, 4, 1));
        period.setDuty("Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.");
        periods = new ArrayList<>();
        periods.add(period);
        company.setPeriods(periods);
        companies.add(company);
        companiesSection = new CompanySection();
        companiesSection.setCompanies(companies);
        resume.setSection(SectionType.EDUCATION, companiesSection);

        company = new Company();
        link = new Link();
        link.setName("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики");
        link.setHyperlink(URI.create("https://spb.university.dns-name"));
        company.setLink(link);
        period = new Period();
        period.setStartDate(LocalDate.of(1993, 9, 1));
        period.setStopDate(LocalDate.of(1996, 7, 1));
        period.setDuty("Аспирантура (программист С, С++)");
        periods = new ArrayList<>();
        periods.add(period);
        period = new Period();
        period.setStartDate(LocalDate.of(1987, 9, 1));
        period.setStopDate(LocalDate.of(1993, 7, 1));
        period.setDuty("Инженер (программист Fortran, C)");
        periods.add(period);
        company.setPeriods(periods);
        companies.add(company);
        companiesSection = new CompanySection();
        companiesSection.setCompanies(companies);
        resume.setSection(SectionType.EDUCATION, companiesSection);

        System.out.println(resume);
    }
}
