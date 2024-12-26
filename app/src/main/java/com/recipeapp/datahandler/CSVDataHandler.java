package com.recipeapp.datahandler;
import com.recipeapp.model.Recipe;
import com.recipeapp.model.Ingredient;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVDataHandler implements DataHandler{
    private String filePath;

    // コンストラクタ
    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    // メソッド
    public String getMode() {
        return "CSV";
    }

    public ArrayList<Recipe> readData() throws IOException {
        // 返却用のレシピリスト
        ArrayList<Recipe> recipes = new ArrayList<>();

        // ファイルを読み込む
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // 初めからデータが存在しない場合
            if (reader.readLine() == null) {
                throw new IOException("No recipes available.");
            }

            while ((line = reader.readLine()) != null) {
                // カンマ区切りの文字列を切り分ける
                String[] splitedNames = line.split(",");

                // 名前と材料リストに分ける
                String name = splitedNames[0];
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                for (int i = 1; i < splitedNames.length; i++) {
                    ingredients.add(new Ingredient(splitedNames[i]));
                }

                // Recipeをインスタンス生成
                Recipe recipe = new Recipe(name, ingredients);
                // リストに追加
                recipes.add(recipe);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return recipes;
    }

    public void writeData(Recipe recipe) {
        // ファイルの書き込み
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.newLine();
            writer.write(recipe.getName() + ",");
            for (int i = 0; i < recipe.getIngredient().size(); i++) {
                if (i == recipe.getIngredient().size() - 1) {
                    writer.write(recipe.getIngredient().get(i).getName());
                }
                else {
                    writer.write(recipe.getIngredient().get(i).getName() + ",");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Recipe> searchData(String keyword) {
        return null;
    }
}
