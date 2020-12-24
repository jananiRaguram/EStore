// package eStoreSearch;
// import org.junit.Test;
// import static org.junit.Assert.*;


// public class AddingProductsTest {
    
//     @Test public void testAddingToBookList() {
//         Books book = new Books();

//         assertTrue("should return true", book.setProductID("123456"));
//         assertFalse("should return false", book.setProductID("1234"));
//         assertFalse("should return false", book.setProductID(""));

//         assertTrue("should return true", book.setDescription("Harry Potter "));
//         assertFalse("should return false", book.setDescription(""));

//         assertTrue("should return true", book.setYear("1234"));
//         assertFalse("should return false", book.setYear(""));
//         assertFalse("should return false", book.setYear("999 "));
//         assertFalse("should return false", book.setYear("10000"));

//         assertTrue("should return true", book.setPrice("99.9"));
//         assertTrue("should return true", book.setPrice("$99.9 "));
//         assertTrue("should return false", book.setPrice(""));

//         assertTrue("should return true", book.setAuthor("J.K Rowling "));
//         assertTrue("should return false", book.setAuthor(""));

//         assertTrue("should return true", book.setPublisher("some Publisher"));
//         assertTrue("should return false", book.setPublisher(""));

//    }

//    @Test public void testAddingToElecList() {
//         Electronics elec = new Electronics();

//         assertTrue("should return true", elec.setProductID("123456"));
//         assertFalse("should return false", elec.setProductID("1234"));
//         assertFalse("should return false", elec.setProductID(""));

//         assertTrue("should return true", elec.setDescription("phone "));
//         assertFalse("should return false", elec.setDescription(""));

//         assertTrue("should return true", elec.setYear("1234"));
//         assertFalse("should return false", elec.setYear(""));
//         assertFalse("should return false", elec.setYear("999 "));
//         assertFalse("should return false", elec.setYear("10000"));

//         assertTrue("should return true", elec.setPrice("99.9"));
//         assertTrue("should return true", elec.setPrice("$99.9 "));
//         assertTrue("should return false", elec.setPrice(""));

//         assertTrue("should return true", elec.setMake("Apple"));
//         assertTrue("should return false", elec.setMake(""));

//     }

//     @Test
//     public void testDuplicateID(){
//         TestCases testCases = new TestCases();
//         assertTrue("return true", testCases.productIDDuplicate(testCases.booksArray(), testCases.elecArray(),  "654321"));
//         assertFalse("return false", testCases.productIDDuplicate(testCases.booksArray(), testCases.elecArray(),  "123456"));

//     }
   
// }
