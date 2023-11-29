import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 매칭_점수 {

    public int solution(String word, String[] pages) {

        Pattern home_url_pattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"");
        Pattern link_url_pattern = Pattern.compile("<a href=\"https://(\\S*)\"");
        Pattern word_pattern = Pattern.compile("\\b(?i)" + word + "\\b");
        Matcher link_url_matcher, word_matcher, home_url_matcher;

        List<String> extUrls;
        Link newLink;
        String body;
        String homeUrl = "";

        Map<String, Link> links = new HashMap<>();

        for (int i = 0; i < pages.length; i++) {
            link_url_matcher = link_url_pattern.matcher(pages[i]);
            home_url_matcher = home_url_pattern.matcher(pages[i]);

            if (home_url_matcher.find()) {
                homeUrl = home_url_matcher.group().split("=")[2].replaceAll("\"", "");
            }

            newLink = new Link(i, homeUrl);
            extUrls = new ArrayList<>();

            while (link_url_matcher.find()) {
                extUrls.add(link_url_matcher.group().split("\"")[1]);
            }
            newLink.setExtUrls(extUrls);

            body = pages[i].split("<body>")[1].split("</body>")[0].replaceAll("[0-9]", " ");
            word_matcher = word_pattern.matcher(body);

            int count = 0;
            while (word_matcher.find()) {
                count += 1;
            }

            newLink.basicPoint = count;
            newLink.setLinkPoint();

            links.put(newLink.url, newLink);
        }

        for (Link link : links.values()) {
            for (String extUrl : link.extUrls) {
                if (links.containsKey(extUrl)) {
                    links.get(extUrl).basicPoint += link.linkPoint;
                }
            }
        }

        double maxPoint = 0;
        int answer = 0;

        for (Link link : links.values()) {
            if (link.basicPoint == maxPoint) {
                if(link.index < answer) answer = link.index;
            } else if (link.basicPoint > maxPoint) {
                answer = link.index;
                maxPoint = link.basicPoint;
            }
        }

        return answer;
    }

    private class Link {
        int index;
        String url;
        String[] extUrls;
        double basicPoint, linkPoint;

        public Link(int index, String url) {
            this.index = index;
            this.url = url;
        }

        public void setExtUrls(List<String> urls) {
            this.extUrls = new String[urls.size()];
            for (int i = 0; i < urls.size(); i++) {
                extUrls[i] = urls.get(i);
            }
        }

        public void setLinkPoint() {
            this.linkPoint = this.basicPoint / extUrls.length;
        }
    }

    @Test
    void test() {
        assertEquals(solution("blind", new String[]{
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                        "<head>\n" +
                        "  <meta charset=\"utf-8\">\n" +
                        "  <meta property=\"og:url\" content=\"https://a.com\"/>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "Blind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n" +
                        "<a href=\"https://b.com\"> Link to b </a>\n" +
                        "</body>\n" +
                        "</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                        "<head>\n" +
                        "  <meta charset=\"utf-8\">\n" +
                        "  <meta property=\"og:url\" content=\"https://b.com\"/>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "Suspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n" +
                        "<a href=\"https://a.com\"> Link to a </a>\n" +
                        "blind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n" +
                        "<a href=\"https://c.com\"> Link to c </a>\n" +
                        "</body>\n" +
                        "</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                        "<head>\n" +
                        "  <meta charset=\"utf-8\">\n" +
                        "  <meta property=\"og:url\" content=\"https://c.com\"/>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "Ut condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n" +
                        "<a href=\"https://a.com\"> Link to a </a>\n" +
                        "</body>\n" +
                        "</html>"}), 0);
    }
}
