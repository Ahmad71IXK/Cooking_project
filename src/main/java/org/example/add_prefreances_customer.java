package org.example;

import java.util.*;

public class add_prefreances_customer {
    private String[] pref_names;
    private String[] allergies_names;
    private String[] unwanted_pref;
    private String[] past_orders;
    private String[] neworders;

    ArrayList<add_prefreances_customer> prefreances = new ArrayList<>();

    public String[] getNeworders() {
        return neworders;
    }

    public void setNeworders(String[] x1) {
        neworders = x1;
    }

    public String[] getPast_orders() {
        return past_orders;
    }

    public void setPast_orders(String[] past_orders) {
        this.past_orders = past_orders;
    }

    public String[] getPref_names() {
        return pref_names;
    }

    public void setPref_names(String[] pref_names) {
        this.pref_names = pref_names;
    }

    public String[] getAllergies_names() {
        return allergies_names;
    }

    public void setAllergies_names(String[] allergies_names) {
        this.allergies_names = allergies_names;
    }

    public String[] getUnwanted_pref() {
        return unwanted_pref;
    }

    public void setUnwanted_pref(String[] unwanted_pref) {
        this.unwanted_pref = unwanted_pref;
    }

    public ArrayList<add_prefreances_customer> getPrefreances() {
        return prefreances;
    }

    public void addPrefreance(add_prefreances_customer pref) {
        this.prefreances.add(pref);
    }

    class prefnames extends main_system {
        add_prefreances_customer x = new add_prefreances_customer();
        Scanner input1 = new Scanner(System.in);
        String input = input1.nextLine().toUpperCase();
        String[] meal = {"chekin biriani"};
        String[] meal2 = {"PASTA WITH VEGETABLES"};

        prefnames(String[] name, String[] name2) {

            x.setPref_names(name);
            x.setAllergies_names(name2);

        }


        }

        class cheif {
            private String[] chefsmain = {"ali", "abass", "ibrahim"};
            private String[] chefother = {"anwar", "joe", "jim"};
            private boolean Validality = false;
            private String[] orderlist;


            public String[] getOrderlist() {
                return orderlist;
            }

            public void setOrderlist(String[] order) {
                order = orderlist;
            }

            public String[] getChefsmain() {
                return chefsmain;
            }

            public String[] getChefother() {
                return chefother;
            }

            public void setValidality(boolean validality) {
                Validality = validality;
            }

            public boolean getValidality() {
                return Validality;
            }
        }

        public class cheif_imp extends cheif {
           // cheif_imp xy = new cheif_imp();


        }
    }









