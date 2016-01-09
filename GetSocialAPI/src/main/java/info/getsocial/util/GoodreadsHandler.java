package info.getsocial.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import info.getsocial.domain.UserBook;

public class GoodreadsHandler extends DefaultHandler{
    private List<UserBook> books;
    private UserBook currentBook;
    /*
     * List of elements we can encounter in this xml and needed to build up the POJO objects.
     */
    private static final String ELEM_BOOK = "book";
    private static final String ELEM_TITLE = "title";
    private static final String ELEM_ID = "id";
    private static final String ELEM_ISBN = "isbn";
    private static final String ELEM_IMAGE_URL = "image_url";
    private static final String ELEM_THUMBNAIL = "small_image_url";
    private static final String ELEM_DESCRIPTION = "description";
    private static final String ELEM_RATING = "average_rating";
    private static final String ELEM_NUM_PAGES = "num_pages";
    private static final String ELEM_RATING_COUNT = "ratings_count";
    private static final String ELEM_LINK = "link";
    private static final String ELEM_AUTHORS = "authors";
    private static final String ELEM_AUTHOR = "author";
    private static final String ELEM_USER = "user";

    private boolean currentElement ;
    /*
     * Needed to know context of element (ie: name can be name of book or name of author).
     */
    private String branchInXML;
    private StringBuilder currentvalue;
    

    
    
    public GoodreadsHandler() {
            currentvalue = new StringBuilder();
            books = new ArrayList<UserBook>();
            branchInXML = ELEM_BOOK; //first element in the xml structure we need to interpret is book
    }
    
    public List<UserBook> getBooks() {
            return books;
    }
    
    /*
     * (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     * This method is called at the start of any dom element
     */
    public void startElement(String uri, String localName, String qName,
                    Attributes attributes) throws SAXException {
            //set current branch and instantiate necessary objects
            if(localName.equalsIgnoreCase(ELEM_BOOK)) {
                    currentBook = new UserBook();
                    branchInXML = ELEM_BOOK;
            } else if(localName.equalsIgnoreCase(ELEM_AUTHORS) || localName.equalsIgnoreCase(ELEM_USER)) {
                    branchInXML = localName;
            }
            currentElement=true;
            currentvalue.setLength(0);
    }
    
    @Override
    public void endElement(String uri, String localName, String qName)
                    throws SAXException {
            /*
             * This structure will interpret the elements or ignore if not relevant.
             * Also we change branch if necessary (ie: review element closes)
             */
            if(branchInXML.equalsIgnoreCase(ELEM_BOOK)) {
                    if(localName.equalsIgnoreCase(ELEM_ID)) {
                            currentBook.setId(new Integer(currentvalue.toString()));
                    } else if(localName.equalsIgnoreCase(ELEM_TITLE)) {
                            currentBook.setTitle(currentvalue.toString());
                    } else if(localName.equalsIgnoreCase(ELEM_ISBN)) {
                            currentBook.setIsbn(currentvalue.toString());
                    } else if(localName.equalsIgnoreCase(ELEM_IMAGE_URL)) {
                            currentBook.setImageURL(currentvalue.toString());
                    } else if(localName.equalsIgnoreCase(ELEM_THUMBNAIL)) {
                            currentBook.setThumbnailURL(currentvalue.toString());
                    } else if(localName.equalsIgnoreCase(ELEM_THUMBNAIL)) {
                            currentBook.setTitle(currentvalue.toString());
                    } else if(localName.equalsIgnoreCase(ELEM_DESCRIPTION)) {
                            currentBook.setDescription(currentvalue.toString());
                    } else if(localName.equalsIgnoreCase(ELEM_RATING)) {
                            currentBook.setRating(new Double(currentvalue.toString()).doubleValue());
                    } else if(localName.equalsIgnoreCase(ELEM_RATING_COUNT)) {
                            currentBook.setRatingCount(new Integer(currentvalue.toString()).intValue());
                    } else if(localName.equalsIgnoreCase(ELEM_NUM_PAGES)) {
                            currentBook.setNumPages(new Integer(currentvalue.toString()).intValue());
                    } else if(localName.equalsIgnoreCase(ELEM_LINK)) {
                            currentBook.setLink(currentvalue.toString());
                    } 
                    
            } else if(branchInXML.equalsIgnoreCase(ELEM_AUTHOR)) {
                    if(localName.equalsIgnoreCase(ELEM_AUTHOR)) {
                            currentBook.addAuthor(currentvalue.toString());
                    }
            }  
            currentElement=false;
            //Close the branch if necessary and return to the parent branch.
            if(localName.equalsIgnoreCase(ELEM_BOOK)) {
                    books.add(currentBook);
            } else if(localName.equalsIgnoreCase(ELEM_AUTHOR)) {
                    branchInXML = ELEM_BOOK;
            }
    }

    @Override
    public void endDocument() throws SAXException {
            super.endDocument();
    }
    
    @Override
    public void characters(char[] ch, int start, int length)
                    throws SAXException {
            if(currentElement) {
                    currentvalue.append(ch, start, length);
            }
    }
}
