package org.onebeartoe.multimedia.juke.links;

import java.util.ArrayList;
import java.util.List;

/**
 * This provides a way to manage link units for a given root URL.
 */
public interface LinkManager 
{

    void setRootUrl(String host, String path) throws Exception;

    LinkUnit getLinksUnitFor(String linkUnitName);

    List<String> getLinkUnitNames();

    void add(LinkUnit linkUnit);

    void remove(LinkUnit linkUnit);

    void clear();

    LinkParser getLinkParser();

    public class LinkUnit {

        public String name;

        public List<Link> links;

        public LinkUnit() {
            links = new ArrayList<Link>();
        }

        public void add(Link link) {
            links.add(link);
        }

        @Override
        public String toString() {
            StringBuffer buf = new StringBuffer(name + ":" + "\n");
            for (Link link : links) {
                buf.append("\t" + link.label + " -> " + link.href + "\n");
            }

            return buf.toString();
        }

    }

    public class Link {

        public String href;

        public String label;

        @Override
        public String toString() {
            return label + " -> " + href;
        }
    }

}
