package mancala;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.File;
import java.nio.file.*;


public class Saver implements Serializable{
    private static final long serialVersionUID = -2809327423608708939L;

    /**
     * Saving serializable object
     */
    public void saveObject(final Serializable toSave, final String filename)throws IOException{
        final Path currentDirector = Paths.get(System.getProperty("user.dir"));
        final Path assetsPath = currentDirector.resolve("assets");

        final File assets = new File("assets");
        if(!assets.exists()){
            //System.out.println("file doesn't exist");
            if(assets.mkdir()){
                //System.out.println("Folder 'assets' created successfully");
            }else{
                throw new IOException("Failed to create the 'assets' folder");
            }
        }

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(assetsPath + "/" +filename))){
            //try-with-resource ensure the stream resources are closed even if there's an exception
            output.writeObject(toSave);
            //System.out.println("Object has been serialized");
        } catch(FileNotFoundException err){
            throw  new IOException("file not found",err);
        }catch (IOException err) {
            throw new IOException("Cannot read the file", err);
        }
    }

    /**
     * Loading serializable object
     */
    public Serializable loadObject(final String filename)throws IOException, ClassNotFoundException{
        final Path currentDirector = Paths.get(System.getProperty("user.dir"));
        final Path assetsPath = currentDirector.resolve("assets");

        try (final ObjectInputStream input = new ObjectInputStream(new FileInputStream(assetsPath + "/" + filename))) {
            //System.out.println("Object has been deserialized ");
            return (Serializable) input.readObject();

        }catch (FileNotFoundException err){
            throw new IOException("file not found",err);
        } catch (IOException err) {
            throw new IOException("cannot read the file", err);
        } catch (ClassNotFoundException err) {
            throw new IOException("class cant be found ", err);
        }
    }
 

}