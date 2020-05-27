package com.company;


import twitter4j.TwitterException;

public class Main {
    public static void main(String[] args) throws TwitterException {

        MethodTesting mT = new MethodTesting();
        //mT.getFeed();
        //mT.searchTweets("love");

        Streamer s = new Streamer();
        s.streamFeed();



    }
}
