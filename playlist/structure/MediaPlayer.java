package structure;
import java.util.*;
import java.io.*;
import java.lang.Math;
public class MediaPlayer{
  class Songs{
    String songname;
    Songs nextsong;
  };
  class SongNotPlayed{
    String song;
    SongNotPlayed next;
    boolean played;

  };
  class Playlists{
    int playmode;
    String listname;
    Playlists nextPlayList;
    Songs head;
  };
  Playlists start;
  Playlists active;
  SongNotPlayed hashTable[]=new SongNotPlayed[10];
  int listCount;
  public MediaPlayer(){
    active=null;
    listCount=0;
    start=null;
    for(int i=0;i<10;i++)
      hashTable[i]=null;
  }
  public void makeNull(){
    for(int i=0;i<10;i++)
      hashTable[i]=null;
  }
  public void createNewPlayList()
  {
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter name of playlist");
    String ch=sc.nextLine();
    Playlists latest=new Playlists();
    latest.listname=ch;
    latest.playmode=1;
    latest.nextPlayList=start;
    latest.head=null;
    start=latest;
    active=start;
    listCount++;
  }
  public boolean isPlayerEmpty()
  {
    if(start==null)
    return true;
    return false;
  }
  public void printPlayList()
  {
    if(!isPlayerEmpty())
    {
      Playlists temp=start;
      int c=1;
      while(temp!=null)
      {
        System.out.println(c+". "+temp.listname);
        c++;
        temp=temp.nextPlayList;
      }

    }
    else
    {
      System.out.println("No playlist");
    }
  }
  public boolean isPlaylistEmpty()
  {
    if(active==null)
    return true;
    return false;
  }

  public void printActivePlayList()
  {
    if(!isPlaylistEmpty())
    {
      System.out.println(active.listname);
    }
    else
    {
      System.out.println("There is no active playlist at this time please choose one");
    }
  }
  public void setActive(int index)
  {
    if(index<=listCount&&index>0)
    {
    if(active!=null)
      makeNull();
    Playlists temp=start;
    int c=1;
    while(c!=index)
    {
      temp=temp.nextPlayList;
      c++;
    }
    active=temp;
    initHash(active);
  }
  else
  {
    System.out.println("Invalid no");
  }
}
public int getFirstSum(String myString,int sum,int pos)
{
  if(myString.length()-1==pos)
    return sum;
  if(pos==0||(pos!=0&&!Character.isLetter(myString.charAt(pos-1))&&Character.isLetter(myString.charAt(pos))))
    sum+=myString.charAt(pos);
  return getFirstSum(myString,sum,pos+1);
}
public int hashKey(String myString)
{
  int sum=getFirstSum(myString,0,0);
  return sum%10;
}
public void initHash(Playlists playlist)
{
  Songs tsong=playlist.head;
  makeNull();
  //System.out.println("init");
  while(tsong!=null)
  {
    int key=hashKey(tsong.songname);
    SongNotPlayed latest=new SongNotPlayed();
    latest.song=tsong.songname;
    latest.played=false;
    latest.next=hashTable[key];
    hashTable[key]=latest;
    tsong=tsong.nextsong;
  }
  // for(int i=0;i<10;i++)
  // {
  //   SongNotPlayed s=hashTable[i];
  //   while(s!=null)
  //   {
  //     System.out.println(s.song);
  //     s=s.next;
  //   }
  // }
}
public void deleteActivePlayList()
{
  if(!isPlaylistEmpty())
  {
    Playlists temp=start;
    if(temp==active)
    {
      start=start.nextPlayList;
      active=null;
      return;
    }
    while(temp.nextPlayList!=active)
    {
      temp=temp.nextPlayList;
    }
    temp.nextPlayList=active.nextPlayList;

  }
  else
  {
    System.out.println("choose playlist");
  }
}
public void resetHash(){
	makeNull();
	initHash(active);
}
public void addSongs(){
  System.out.println("choose active playlist");
  Scanner scs=new Scanner(System.in);
  int f=scs.nextInt();
  setActive(f);
  if(!isPlaylistEmpty())
  {
    Songs latest=new Songs();
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter song name");
    String nsong=sc.nextLine();
    latest.songname=nsong;
    latest.nextsong=active.head;
    active.head=latest;
    System.out.println("Song SuccessFully added");
    		resetHash();
  }
}
public void peekPlayList(){
  if(!isPlaylistEmpty())
  {
    System.out.println("songs of"+active.listname+":-");
    Songs peek=active.head;
    if(peek==null)
    {
      System.out.println("Sorry there are no songs in the playlist");
    }
    int c=1;
    while(peek!=null)
    {
      System.out.println(c+". "+peek.songname);
      peek=peek.nextsong;
      c++;
    }
  }
  else
  {
    System.out.println("First choose playlist");
  }
}
public void setModeNormal()
{
  peekPlayList();
}
public void setModeRepeatPlayList()
{
  int limit=10;
  int countLimit=0;
  while(countLimit<limit)
  {
    peekPlayList();
    countLimit++;
  }
}
public int getSongsCount(Songs head){
	int count=0;
	Songs temp=head;
	while(temp!=null){
		count++;
		temp=temp.nextsong;
	}
	return count;
}
public boolean isFalsePresent( SongNotPlayed start){
	while(start!=null){
		if(start.played==false)
			return true;
		start=start.next;
	}
	return false;
}
public void setShuffle()
{
  int limit=getSongsCount(active.head);
  int x;
  System.out.println(limit+"ss");
  while(limit!=0)
  {
    while(true)
    {
        Random random = new Random();
        x = random.nextInt(10);
      //System.out.println(x);
      SongNotPlayed s=hashTable[x];
      if(s!=null)
        break;
      //  System.out.println("kk");


    }
    System.out.println(x);

     SongNotPlayed start1=hashTable[x];
    while(start1!=null)
    {
      if(start1.played!=true)
      {
        start1.played=true;
        System.out.println(start1.song);
        limit--;
        System.out.println(limit);
      }
      start1=start1.next;

  }
  }
  for(int i=0;i<10;i++)
  {

    if(hashTable[i]!=null)
    {
      SongNotPlayed start2=hashTable[i];
      while(start2!=null)
      {
        start2.played=false;
        start2=start2.next;
      }
    }
  }
}
public void playSongs(int mode)
{
  if(!isPlaylistEmpty()){
    active.playmode=mode;
    switch(mode){
      case 1:
        setModeNormal();
        break;
      case 2:
        setModeRepeatPlayList();
        break;
      case 3:
        setShuffle();
        break;
      default:
        System.out.println("mode not in service");
    }
  }
}

}
