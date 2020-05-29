package com.company;

import twitter4j.*;

public class Streamer {

    public static void streamFeed() throws TwitterException {

        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        Twitter twitter = new TwitterFactory().getInstance();
        long myID = twitter.getId();

        StatusListener listener = new StatusListener() {

            @Override
            public void onException(Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice arg) {
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {

            }

            @Override
            public void onStallWarning(StallWarning warning) {
            }

            @Override
            public void onStatus(Status status) {

                //Filter out replies, retweets, and mentions by only keeping status if source is friend.

                try {
                    if(!status.isRetweet() && twitter.showFriendship(myID, status.getUser().getId()).isSourceFollowingTarget()) {

                        String username = "@" + status.getUser().getScreenName();
                        String displayName = status.getUser().getName();
                        String tweet = status.getText();

                        System.out.println(displayName + " (" + username + ") - " + tweet + "\n");
                    }
                } catch (TwitterException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }
        };

        twitterStream.addListener(listener);

        // Uses friends (user's the auth user follows) ids to filter stream
        
        long cursor = -1;
        long[] friends = twitter.getFriendsIDs(cursor).getIDs();
        FilterQuery filter = new FilterQuery(friends);

        twitterStream.filter(filter);
    }
}
