package com.example.mrust.json_231214;

/**
 * Created by mrust on 31.12.2014.
 */
public class Event {

    protected static int id = 0;
    protected static int event_category_id  = 0;
    protected static int root_event_category_id = 0;
    protected static String root_event_category_name = null;
    protected static String root_meta_name = null;
    protected static String name = null;
    protected static String icon_marker = null;
    protected static int phone = 0;
    protected static String start_datetime = null;
    protected static String end_datetime = null;
    protected static String time_zone = null;
    protected static String ticket_url = null;
    protected static Long latitude ;
    protected static Long longtitude ;
    protected static String images_medium = null;
    protected static String images_square = null;
    protected static String images_poster = null;
    protected static String images_android = null;

    public static String getStart_datetime() {
        return start_datetime;
    }

    public static void setStart_datetime(String start_datetime) {
        Event.start_datetime = start_datetime;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Event.id = id;
    }

    public static int getEvent_category_id() {
        return event_category_id;
    }

    public static void setEvent_category_id(int event_category_id) {
        Event.event_category_id = event_category_id;
    }

    public static int getRoot_event_category_id() {
        return root_event_category_id;
    }

    public static void setRoot_event_category_id(int root_event_category_id) {
        Event.root_event_category_id = root_event_category_id;
    }

    public static String getRoot_event_category_name() {
        return root_event_category_name;
    }

    public static void setRoot_event_category_name(String root_event_category_name) {
        Event.root_event_category_name = root_event_category_name;
    }

    public static String getRoot_meta_name() {
        return root_meta_name;
    }

    public static void setRoot_meta_name(String root_meta_name) {
        Event.root_meta_name = root_meta_name;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Event.name = name;
    }

    public static String getIcon_marker() {
        return icon_marker;
    }

    public static void setIcon_marker(String icon_marker) {
        Event.icon_marker = icon_marker;
    }

    public static int getPhone() {
        return phone;
    }

    public static void setPhone(int phone) {
        Event.phone = phone;
    }

    public static String getEnd_datetime() {
        return end_datetime;
    }

    public static void setEnd_datetime(String end_datetime) {
        Event.end_datetime = end_datetime;
    }

    public static String getTime_zone() {
        return time_zone;
    }

    public static void setTime_zone(String time_zone) {
        Event.time_zone = time_zone;
    }

    public static String getTicket_url() {
        return ticket_url;
    }

    public static void setTicket_url(String ticket_url) {
        Event.ticket_url = ticket_url;
    }

    public static Long getLatitude() {
        return latitude;
    }

    public static void setLatitude(Long latitude) {
        Event.latitude = latitude;
    }

    public static Long getLongtitude() {
        return longtitude;
    }

    public static void setLongtitude(Long longtitude) {
        Event.longtitude = longtitude;
    }

    public static String getImages_medium() {
        return images_medium;
    }

    public static void setImages_medium(String images_medium) {
        Event.images_medium = images_medium;
    }

    public static String getImages_square() {
        return images_square;
    }

    public static void setImages_square(String images_square) {
        Event.images_square = images_square;
    }

    public static String getImages_poster() {
        return images_poster;
    }

    public static void setImages_poster(String images_poster) {
        Event.images_poster = images_poster;
    }

    public static String getImages_android() {
        return images_android;
    }

    public static void setImages_android(String images_android) {
        Event.images_android = images_android;
    }
}
