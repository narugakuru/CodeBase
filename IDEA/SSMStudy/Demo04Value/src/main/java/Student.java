package main.java;

import java.util.*;

public class Student {
    private String name;
    private int num;
    private Map<String,String> hashMap;
    private List<String> list;
    private String[] books;
    private Set<String> games;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", num=" + num +
                ", hashMap=" + hashMap +
                ", list=" + list +
                ", books=" + Arrays.toString(books) +
                ", games=" + games +
                '}';
    }

    public Student() {
    }
//不需要
//    public Student(String name, int num, Map<String, String> hashMap, List<String> list, String[] books, Set<String> games) {
//        this.name = name;
//        this.num = num;
//        this.hashMap = hashMap;
//        this.list = list;
//        this.books = books;
//        this.games = games;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Map<String, String> getHashMap() {
        return hashMap;
    }

    public void setHashMap(Map<String, String> hashMap) {
        this.hashMap = hashMap;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String[] getBooks() {
        return books;
    }

    public void setBooks(String[] books) {
        this.books = books;
    }

    public Set<String> getGames() {
        return games;
    }

    public void setGames(Set<String> games) {
        this.games = games;
    }
}
