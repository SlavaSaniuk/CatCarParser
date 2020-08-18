package by.bsac.core;

import by.bsac.web.html.Link;

public interface Linked {

    Link getLinked();

    default Link newLinked(String a_href_value) {
        return new Link(a_href_value);
    }
}
