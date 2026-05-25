package webapp.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.Writer;
import webapp.model.Resume;

public class XmlParser {
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public XmlParser(Class<?>... classesToBeBound) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(classesToBeBound);
            marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            // marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            unmarshaller = ctx.createUnmarshaller();
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    public Resume unmarshall(Reader reader) {
        try {
            return (Resume) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new IllegalStateException("Java is not in an appropriate state for unmarshall from XML", e);
        }
    }

    public void marshall(Object instance, Writer writer) {
        try {
            marshaller.marshal(instance, writer);
        } catch (JAXBException e) {
            throw new IllegalStateException("Java is not in an appropriate state for marshall to XML", e);
        }
    }
}