package io.github.nandandesai.instagram4j.requests.payload;


import java.util.List;

/**
 * Tag Feed User tag container (because usertags ist not a list, but usertags.in is)
 *
 * @author Willy Hille
 *
 */
public class InstagramUserTagsContainer {

    private List<InstagramFeedUserTag> in;

    public List<InstagramFeedUserTag> getIn() {
        return in;
    }

    public void setIn(List<InstagramFeedUserTag> in) {
        this.in = in;
    }
}
