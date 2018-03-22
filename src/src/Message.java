
import java.io.File;

/**
 * @author Roman, Ali
 *
 * Implementing the Serializable interface, turns the message to binary
 */

import java.io.Serializable;
import java.sql.Timestamp;

public class Message implements Serializable{
    private int command;
    private String[] content;
    private File file= null;

    /**
     * Constructor for message with only a command
     * @param command Command from client.
     */
    public Message(int command){
        this.command=command;
    }

    /**
     *
     * @param command Command from client.
     * @param content Content of the message which will be put in a
     */
    public Message(int command, String[]content){
        this.command=command;
        this.content= content;
    }
    public Message(int command, String[]content, File file){
        this.command=command;
        this.content= content;
        this.file=file;
    }

    public int getCommand() {
        return command;
    }

    public String[] getContent() {
        return content;
    }

    public File getFile() {
        return file;
    }
}