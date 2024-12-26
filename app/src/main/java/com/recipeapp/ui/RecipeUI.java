package com.recipeapp.ui;

import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }
    
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    private void displayRecipes() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        
        try {
            recipes = dataHandler.readData();

            System.out.println("Recipes:");
            for (Recipe recipe : recipes) {
                System.out.println("-----------------------------------");
                System.out.print("Recipe Name: ");
                System.out.println(recipe.getName());
                System.out.print("Main Ingredients: ");

                // 材料リストの最後のカンマを表示しない
                for (int i = 0; i < recipe.getIngredient().size(); i++) {
                    if (i == recipe.getIngredient().size() - 1) {
                        System.out.print(recipe.getIngredient().get(i).getName());
                    }
                    else {
                        System.out.print(recipe.getIngredient().get(i).getName() + ",");
                    }
                }

                System.out.println();
            }
            System.out.println("-----------------------------------");
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private void addNewRecipe() throws IOException{
        String nameLine;
        String ingredientLine = "";
        String name;
        ArrayList<Ingredient> ingredients = new ArrayList<>();

        try {
            System.out.println("Adding a new recipe.");
            System.out.print("Enter recipe name: ");

            // 名前を入力
            nameLine = reader.readLine();
            name = nameLine;

            System.out.println("Enter ingredients (type 'done' when finished):");

            // 材料を入力
            while (!(ingredientLine.equals("done"))) {
                System.out.print("Ingredient: ");
                ingredientLine = reader.readLine();
                if (!(ingredientLine.equals("done"))) {
                    ingredients.add(new Ingredient(ingredientLine));
                }
            }

            // レシピをインスタンス生成
            Recipe recipe = new Recipe(name, ingredients);
            dataHandler.writeData(recipe);
            
            System.out.println("Recipe added successfully.");
        }
        catch (IOException ex) {

        }
    }
}
