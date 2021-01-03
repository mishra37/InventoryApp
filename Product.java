// --== CS400 File Header Information ==--
// Name: Uday Malhotra
// Email: umalhotra@wisc.edu
// Team: LB
// Role: Data Wrangler
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: This is completely written by Uday Malhotra, except a few changes made by me.
/**
 * This is the Product class. Objects of this class will be stored in the hashtable.
 * @author Uday Malhotra
 */
public class Product {
  private String type;
  private String name;
  private String manufacturer;
  private Long barcode;
  private Double price;

  /**
   * constructor that initializes attributes of the Product
   * 
   * @param name
   * @param type
   * @param manufacturer
   * @param barcode
   * @param price
   */
  public Product(String name, String type, String manufacturer, Long barcode, Double price) {
    this.name = name;
    this.type = type;
    this.manufacturer = manufacturer;
    this.barcode = barcode;
    this.price = price;
  }

  /**
   * setter for name private field
   * 
   * @param name- String name passed to change
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * getter for name private field
   * 
   * @return name of product
   */
  public String getName() {
    return this.name;
  }

  /**
   * setter for setType private field
   * 
   * @param type - String type passed to change
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * getter for type private field
   * 
   * @return type of the product
   */
  public String getType() {
    return this.type;
  }

  /**
   * setter for price private field
   * 
   * @param price- double price passed to change
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * getter for price private field
   * 
   * @param return price of the product
   */
  public Double getPrice() {
    return this.price;
  }

  /**
   * setter for manufacturer private field
   * 
   * @param manufacturer - String manufacturer passed to change
   */
  public void setManufacturer(String manu) {
    this.manufacturer = manu;
  }

  /**
   * getter for manufacturer private field
   * 
   * @return manufacturer of the product
   */
  public String getManufacturer() {
    return this.manufacturer;
  }

  /**
   * setter for barcode private field
   * 
   * @param code- long code passed to change barcode
   */
  public void setBarcode(long code) {
    this.barcode = code;
  }

  /**
   * getter for barcode private field
   * 
   * @return barcode of the product
   */
  public long getBarcode() {
    return this.barcode;
  }

}
