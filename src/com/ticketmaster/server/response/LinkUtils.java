package com.ticketmaster.server.response;

import com.ticketmaster.server.FileUtils;
import com.ticketmaster.server.Server;
import com.ticketmaster.server.model.Link;
import com.ticketmaster.server.tictactoe.GameBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yen.hoang on 10/2/15.
 */
public class LinkUtils {

  public static String convertLinksToOutputFormat(List<Link> links) {
    StringBuilder sb = new StringBuilder();
    sb.append("\"links\": [");
    for (Link link : links) {
      sb.append("{");
      sb.append("\"rel\": \"").append(link.getRel()).append("\",");
      sb.append("\"href\": \"").append(link.getHref()).append("\"");
      sb.append("},");
    }
    // remove last comma
    int idx = sb.lastIndexOf(",");
    sb.deleteCharAt(idx);
    sb.append("]");

    return sb.toString();
  }

  public static List<Link> convertGameToLinks(GameBoard gameBoard) {

    List<Link> links = new ArrayList<>();

    String localhost = "http://localhost:" + Server.getPortNumber();
    Link selfLink = new Link("self", localhost + "/games/" + gameBoard.getGame_id());
    Link spotLink = new Link("mark_spot", localhost + "/games/"
        + gameBoard.getGame_id() + "/spots/{{spot_id}}");

    links.add(selfLink);
    links.add(spotLink);
    return links;
  }

  public static String getGameLinks(GameBoard gameBoard) {
    return convertLinksToOutputFormat(convertGameToLinks(gameBoard));
  }
}
