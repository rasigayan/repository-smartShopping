package com.example.rasi.SmartTshirts;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import android.content.res.Resources;

public class ShoppingCartHelper {

    public static final String PRODUCT_INDEX = "PRODUCT_INDEX";

    private static List<Product> catalog;
    private static Map<Product, ShoppingCartEntry> cartMap = new HashMap<Product, ShoppingCartEntry>();

    public static List<Product> getCatalog(Resources res){
        if(catalog == null) {
            catalog = new Vector<Product>();
            catalog.add(new Product("Live's", res
                    .getDrawable(R.drawable.ts1),
                    "Retro is back in style. Enjoy this vintage-inspired ringer tee featuring contrasting cuffs and neckline for a sporty yet casual look. A durable yet soft shirt that will quickly become a wardrobe favorite. Select a design from our marketplace or customize it and unleash your creativity!", 18.00));
            catalog.add(new Product("Polo", res
                    .getDrawable(R.drawable.ts2),
                    "A polo shirt, also known as a golf shirt and tennis shirt, is a form of shirt with a collar, a placket with typically two or three buttons, and an optional pocket. All three terms may be used interchangeably. Polo shirts are usually made of knitted cloth (rather than woven cloth), usually piqué\n" +
                            "\" + cotton or, less commonly, silk, merino wool, or synthetic fibers. A dress-length version of the shirt is called a polo dress", 24.99));
            catalog.add(new Product("Emarald", res
                    .getDrawable(R.drawable.ts3),
                    "Being Sri Lanka's leading shirt brand, boasting a shirt manufacturing experience of over 50 years; We realize the rising potential of the average Sri Lankan man in being a true trendsetter in the fashion industry today", 14.99));

            catalog.add(new Product("Signature", res
                    .getDrawable(R.drawable.ts4),
                    "We sell the hottest T-Shirts on WorldWideWeb. New designs added every day. Wear only the BEST quality T-Shirts with UNIQUE designs.\n" +
                            "PayPal is our preferred payment method. It is the safest, most reliable, and fastest service available", 18.59));

            catalog.add(new Product("Guess", res
                    .getDrawable(R.drawable.ts5),
                    "Guess acquired the expertise of professional designer & apparel engineer Roberto Inglesi who could walk the talk. With the experience and exposure of working with some of the " +
                            "world’s most talented individuals as well as internationally acclaimed fashion brands, his experience and exposure in shirt making cannot be matched by many", 20.00));

            catalog.add(new Product("Spirit", res
                    .getDrawable(R.drawable.ts6),
                    "With a rich heritage in precision shirt making, we give you an internationally acclaimed product spearheaded by our very own Head of Design -Signor Roberto Inglesi; providing the very best to those who deserve it", 45.00));

            catalog.add(new Product("Bare Foot", res
                    .getDrawable(R.drawable.ts7),
                    "Being Sri Lanka's leading shirt brand, boasting a shirt manufacturing experience of over 50 years; We realize the rising potential of the average Sri Lankan man in being a true trendsetter in the fashion industry today", 85.00));

            catalog.add(new Product("soreno", res
                    .getDrawable(R.drawable.ts8),
                    "Soreno acquired the expertise of professional designer & apparel engineer Roberto Inglesi who could walk the talk. With the experience and exposure of working with some of the world’s most talented individuals as well as internationally acclaimed fashion brands, his experience and exposure in shirt making cannot be matched by many", 65.00));

            catalog.add(new Product("Odel", res
                    .getDrawable(R.drawable.ts9),
                    "Fabric and embellishments plays an integral part in producing a perfect shirt. This has been perfected by our procurement team through the years. However what we lacked in bridging this gap is the high level of professionalism and exposure in design and techniques", 42.00));



        }

        return catalog;
    }

    public static void setQuantity(Product product, int quantity) {
        // Get the current cart entry
        ShoppingCartEntry curEntry = cartMap.get(product);

        // If the quantity is zero or less, remove the products
        if(quantity <= 0) {
            if(curEntry != null) {
                removeProduct(product);
            }
            return;
        }

        // If a current cart entry doesn't exist, create one
        if(curEntry == null) {
            curEntry = new ShoppingCartEntry(product, quantity);
            cartMap.put(product, curEntry);
            return;
        }

        // Update the quantity
        curEntry.setQuantity(quantity);
    }

    public static int getProductQuantity(Product product) {
        // Get the current cart entry
        ShoppingCartEntry curEntry = cartMap.get(product);

        if(curEntry != null)
            return curEntry.getQuantity();

        return 0;
    }

    public static void removeProduct(Product product) {
        cartMap.remove(product);
    }

    public static List<Product> getCartList() {
        List<Product> cartList = new Vector<Product>(cartMap.keySet().size());
        for(Product p : cartMap.keySet()) {
            cartList.add(p);
        }

        return cartList;
    }


}