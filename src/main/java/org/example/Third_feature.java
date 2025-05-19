package org.example;
import java.util.Objects;
import java.util.Scanner;

public class Third_feature extends add_prefreances_customer {

    public class Third_feature1 extends prefnames {

        public Third_feature1(String[] name, String[] name2) {
            super(name, name2);
        }

        public class miniclass extends cheif_imp {

            add_prefreances_customer x = new add_prefreances_customer();
            // !!! TEMPORARILY COMMENTING OUT SCANNER IN MINICLASS CONSTRUCTOR-LIKE AREA !!!
            // Scanner input1 = new Scanner(System.in);
            // String input = input1.nextLine().toUpperCase();

            public void spilt_work() {
                System.out.println("do you want deseret or one of the main dishis by D / M");
                // This Scanner IS STILL ACTIVE and needed for spilt_work
                Scanner input1ForSpiltWork = new Scanner(System.in);
                String name = input1ForSpiltWork.nextLine();
                String[] x1 = x.getPast_orders();

                if (Objects.equals(name, "D")) {
                    String[] chef_list;
                    chef_list = getChefother();

                    for (String chef : chef_list) {
                        if (this.getValidality() == true) {
                            this.setOrderlist(x1);
                        }
                    }
                } else {
                    String[] chef_list;
                    chef_list = getChefsmain();
                    for (String chef : chef_list) {
                        if (this.getValidality() == true) {
                            this.setOrderlist(x1);
                        }
                    }
                }
            }
        }
    }
}