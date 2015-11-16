package info.getsocial.util;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import info.getsocial.domain.Book;

public class GoodreadsParser {

    public GoodreadsParser() {

    }
    
    public List<Book> parseBookWithReviews(URL url) throws RuntimeException {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            
            GoodreadsHandler handler = new GoodreadsHandler();
            try {
                    SAXParser sp = spf.newSAXParser();
                    XMLReader xr = sp.getXMLReader();
                    xr.setContentHandler(handler);
                    xr.parse(new InputSource(url.openConnection().getInputStream()));
            } catch (ParserConfigurationException e) {
                    throw new RuntimeException(e);
            } catch (SAXException e) {
                    throw new RuntimeException(e);
            } catch (IOException e) {
                    throw new RuntimeException(e);
            }
            /*
             * getBook returns a book object from the API call
             * Note: this book does not contain a list of reviews.
             */
            return handler.getBooks();
    }

}
