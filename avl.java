/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author suraj
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
class AVL {
  static  class points{
    int x;
    int y;
    int z;}

  static class node{
points data;
int height;
node left;
node right;
int index;
node(points x,int n){
data=x;
height=1;
index=n;
}
}
  
static class Tree{
node root;
int height(node root){
if(root==null)
    return 0;
else 
    return root.height;
}
 int max(int a,int b){
 if(a>b)
     return a;
 else return b;}
node singleLRotate(node root){
node right=root.right;
node temp=right.left;
right.left=root;
root.right=temp;
root.height=max(height(root.left),height(root.right))+1;
right.height=max(height(right.left),height(right.right))+1;
return right;}
node singleRRotate(node root){
node left=root.left;
node temp=left.right;
left.right=root;
root.left=temp;
root.height=max(height(root.left),height(root.right))+1;
left.height=max(height(left.left),height(left.right))+1;
return left;
}
node doubleLRotate(node root){
    root.right=singleRRotate(root.right);
    return singleLRotate(root);
}
node doubleRRotate(node root){
    root.left=singleLRotate(root.left);
    return singleRRotate(root);
}
int balanceFactor(node root){
if(root==null)
    return 0;
else return height(root.left)-height(root.right);
}
node insert(node root,points data,int n){
if(root==null)
    return (new node(data,n));
if(data.y<root.data.y)
    root.left=insert(root.left,data,n);
else if(data.y>root.data.y)
    root.right=insert(root.right,data,n);
else return root;
root.height=1+max(height(root.left),height(root.right));
int bf=balanceFactor(root);
if(bf>1&&data.y<root.left.data.y)
    return singleRRotate(root);
if(bf<-1&&data.y>root.right.data.y)
    return singleLRotate(root);
if(bf>1&&data.y>root.left.data.y)
    return doubleRRotate(root);
if(bf<-1&&data.y<root.right.data.y)
    return doubleLRotate(root);
return root;
}node minValueNode(node Node)
    {
        node current = Node;
 
        /* loop down to find the leftmost leaf */
        while (current.left != null)
           current = current.left;
 
        return current;
    }
 
void deletenode(points x){
root=deletenode(x,root);}
node deletenode(points x,node root){
if(root==null)
    return root;
if(x.y<root.data.y)
    root.left=deletenode(x,root.left);
else if(root.data.y<x.y)
    root.right=deletenode(x,root.right);
else{
if((root.left==null)||(root.right==null)){
node temp=null;
if(temp==root.left)
    temp=root.right;
else
    temp=root.left;
if(temp==null)
{temp=root;
root=null;
}
else 
    root=temp;
}
else{
node temp=minValueNode(root.right);
root.data=temp.data;
root.right=deletenode(temp.data,root.right);
}
}
if(root==null)
    return root;
root.height=max(height(root.left),height(root.right))+1;
int b=height(root.left)-height(root.right);
if(b>1 &&(height(root.left.left)-height(root.left.right)>=0))
 return singleRRotate(root);   
if(b>1 &&(height(root.left.left)-height(root.left.right)<0))
 return doubleRRotate(root);   
if(b<-1&&(height(root.right.left)-height(root.right.right)<=0))
    return singleLRotate(root);
if(b<-1&&(height(root.right.left)-height(root.right.right)>0))
    return doubleLRotate(root);
return root;
}
void inOrder(){
inOrder(root);}
void inOrder(node root){
    
if(null==root)
    return;
    inOrder(root.left);
System.out.println("("+root.data.y+","+root.data.z+")");
    inOrder(root.right);
}
void preOrder(node root){
   
if(null==root)
    return;
System.out.println("("+root.data.y+","+root.data.z+")");
    preOrder(root.left);

    preOrder(root.right);
}
void treeInsert(points a,int n){
root=insert(root,a,n);}

         node inSuccesor(node root) {
           node temp=root.right;
           while(temp.left!=null)
             temp=temp.left;
             return temp;
        }
 static node findMax(node root){
 while(root.right!=null)
     root=root.right;
 return root;
 }
 static node find(node root,points key){
 if(root==null)
     return null;
 else if(root.data.y==key.y)
     return root;
 else if(root.data.y>key.y)
     return find(root.left,key);
 else 
     return find(root.right,key);
 }
static node findpre(node root,points key){
    node t=find(root,key);
    if(t.left!=null)
        return findMax(t.left);
    node pre=null;
    while(root!=null){
    if(key.y==root.data.y)
        break;
    else if(key.y<root.data.y){
            root=root.left;}
    else if(key.y>root.data.y){
    pre=root;
    root=root.right;
    }
}
return pre;
}
node delpre(node root,points x){
  node temp=findpre(root,x);
while(temp!=null&&temp.data.z<x.z){
root=deletenode(temp.data,root);
temp=findpre(root,x);
}
return root;

}
    }
static void swap(points a,points b){
points temp=new points();
temp.x=a.x;
temp.y=a.y;
temp.z=a.z;
a.x=b.x;
a.y=b.y;
a.z=b.z;
b.x=temp.x;
b.y=temp.y;
b.z=temp.z;
}
static int randpartition(points arr[],int s, int e){
Random r=new Random();
    int rand=r.nextInt(e-s+1)+s;
    swap(arr[rand],arr[e]);
    points pivot=arr[e];
int i=s-1;
for(int j=s;j<=e-1;j++){
if(arr[j].x<pivot.x){
i++;
swap(arr[i],arr[j]);
}
else if(arr[j].x==pivot.x){
if(arr[j].y<pivot.y){i++;
swap(arr[i],arr[j]);
}
else if(arr[j].y==pivot.y){
if(arr[j].z<pivot.z){i++;
    swap(arr[i],arr[j]);}
}
}
}
swap(arr[i+1],arr[e]);
return i+1;
}

static void  quicksort(points arr[],int s,int e){
    if(s<e){
int p=randpartition(arr,s,e);
quicksort(arr,s,p-1);
quicksort(arr,p+1,e);
}}
static boolean isInsertible(node root ,points p){  
     if(root==null)
        return true;
    if(root.data.y>=p.y&&root.data.z>=p.z)
return false;
    else if(root.data.y<=p.y&&root.data.z<p.z)
        return true;
    else if(root.data.y>p.y)
        return isInsertible(root.left,p);
    else if(root.data.y<p.y)
        return isInsertible(root.right,p);

   return false;
}
static class custarray{
    int len;
    int arr[];
    custarray(int n){
    int len=0;
    arr=new int[n];
    }
}
static custarray ThreeDMaxima(node root,points arr[]){
 int len=arr.length-1;

 custarray a=new custarray(arr.length);
 
 for(int i=len;i>=0;i--)
 {if(isInsertible(root,arr[i])){
    a.arr[a.len++]=i;
  {
    root=(new Tree()).insert(root, arr[i], i);
     root=(new Tree()).delpre(root,arr[i]);
      
 }
 }
 }
 
 return a;
}
public static void main(String args[]) throws FileNotFoundException{
    
int t;
File f=new File("./input.txt");
      
Scanner s=new Scanner(f);
t=s.nextInt();
      try {
          FileWriter writer=new FileWriter("./output.txt");
          BufferedWriter r=new BufferedWriter(writer);
      
 points arr[]=new points[t];
for(int i=0;i<t;i++){
    arr[i]=new points();
    arr[i].x=s.nextInt();
    arr[i].y=s.nextInt();
    arr[i].z=s.nextInt();
}
quicksort(arr,0,arr.length-1);
Tree root=new Tree();
custarray a=ThreeDMaxima(root.root,arr);
r.write(""+a.len+"\n");
System.out.println(a.len);
for(int i=a.len-1;i>=0;i--){
    r.write(""+a.arr[i]+"\n");
    System.out.println(a.arr[i]);}
r.close();
} catch (IOException ex) {
          Logger.getLogger(AVL.class.getName()).log(Level.SEVERE, null, ex);
      }
}
}

