import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;
import java.io.*;

public class App {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");
            String choice = reader.readLine();
            int choiceNum = Integer.parseInt(choice);
            
            // DataHandlerのインスタンス生成、コンストラクタ実行
            // 入力が２のとき JSONDataHandler
            if (choiceNum == 2) {
                JSONDataHandler jsonDataHandler = new JSONDataHandler();
                RecipeUI recipeUI = new RecipeUI(jsonDataHandler);
                recipeUI.displayMenu();
            }
            // 入力が１ または 不正な入力のとき CSVDataHandler
            else {
                CSVDataHandler csvDataHandler = new CSVDataHandler();
                RecipeUI recipeUI = new RecipeUI(csvDataHandler);
                recipeUI.displayMenu();
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}