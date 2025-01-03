import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class fileUtility {

    public static void main(String[] args) {
    
        //Problem stmt : we have a dir, so we have to search for a particular keyword in that whole dir.
        //this code uses Executor Service to create threads and find for a particular keyword in a directory containing multiple file.
        //used Java NIO
        String keyword = "error";
        //your path
        String PATH ="C:\\Users\\XX\\A\\Documents\\AA\\AA A\\testfiles"; 
        File dir =  new File(PATH);
        // System.out.println(dir);


        searchForFilesInDir(dir,keyword);
    }

    public static void searchForFilesInDir(File directory, String keyword){

        ExecutorService es = Executors.newFixedThreadPool(4);

        File [] files = directory.listFiles();


        if(files!=null)
        {
            
            for(File f : files)
            {
                es.submit(  ()->{

                    if(keywordSearcher(f,keyword))
                    {
                        System.out.println("keywd found in file :: "+f.toString());
                    }
                } );   
            }
        }
        es.shutdown();

    }


    public static boolean keywordSearcher(File dir,String keyword){
            boolean flag = false;
            try {
                flag = Files.lines(dir.toPath()).anyMatch(lines->lines.contains(keyword));    
            } catch (Exception e) {
                e.printStackTrace();
            }
            return flag;
    }

}