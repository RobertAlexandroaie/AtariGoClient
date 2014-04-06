package atarigoclient;

public class AtariGoClient {

    public static void main(String[] args){
        Client clientul=new Client("127.0.0.1",6666);
        clientul.play();
    }
}
