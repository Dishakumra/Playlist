import java.util.*;
import java.io.*;
import structure.*;
public class Playlist{
  public static void main(String args[])
  {
    MediaPlayer m=new MediaPlayer();
    do{
    System.out.println("Welcome to mymediaplayer");
    int choice;
    int inter;
    System.out.println("1 :- Create a new playlist");
      System.out.println("2 :- Print All the PlayLists ");
      System.out.println("3 :- print the active PlayList ");
      System.out.println("4 :- Enter the Playlist no. to start it ");
      System.out.println("5:- add Song to The Playlist");
      System.out.println("6 :- See songs in active Playlist");
        System.out.println("7:- delete the Playlist");

          System.out.println("8:- set modes out of (1 for normal 2 for repeat playlist 3 for shuffle play)");
          Scanner sc=new Scanner(System.in);
          choice=sc.nextInt();
          switch(choice){
            case 1:
              m.createNewPlayList();
              break;
            case 2:
              m.printPlayList();
              break;
            case 3:
              m.printActivePlayList();
              break;
            case 4:
              inter=sc.nextInt();
              m.setActive(inter);
              break;
            case 5:
              m.addSongs();
              break;
            case 6:
              m.peekPlayList();
              break;
            case 7:
              m.deleteActivePlayList();
              break;
            case 8:
              inter=sc.nextInt();
              m.playSongs(inter);
              break;
          }
          if(choice==0)
            break;
        }while(true);
  }
}
