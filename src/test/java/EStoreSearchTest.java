// package eStoreSearch;

// import static org.junit.Assert.*;
// import java.util.ArrayList;
// import org.junit.Before;
// import org.junit.Test;

// public class EStoreSearchTest {
//     private ArrayList<Products> testList;

//     @Before
//     public void createList(){
//         ArrayList<Products> testList = new ArrayList<>();
//         testList.add(new Books("000025", "Absolute Java", "199.95","2015", "Walter Savitch, Kenrich Mock", "Pearson" ));
//         testList.add(new Electronics("000107", "MacBook Air 11\" Intel Dual-Core i5 1.6GHz", "1099.99","2013",  "Apple Inc."));
//         testList.add(new Books("654321", "java programming is good",  "50",  "2016", "Someone", "Programmer"));
//         testList.add(new Electronics("123456", "C programming", "1099.99", "2014", "debian"));
//         this.testList = testList;
//     }

//     @Test public void testMatchEachWord(){
//         EStoreSearch test = new EStoreSearch();
//         ArrayList<Integer> match = new ArrayList<>();

//         match.add(0);
//         match.add(2);
//         assertEquals(match, test.matchEachWord(testList, "java"));

//         match.clear();
//         match.add(2);
//         match.add(3);
//         assertEquals(match, test.matchEachWord(testList, "programming" ));
        
//         match.clear();
//         match.add(1);
//         assertEquals(match, test.matchEachWord(testList,"11\""));
//     }

//     @Test public void testFindIntersection(){
//         ArrayList<Integer> matches = new ArrayList<Integer>();
//         EStoreSearch test = new EStoreSearch();

//         matches.add(2);
//         assertEquals(matches, test.findIntersection(testList, "java Programming"));

//         matches.clear();
//         matches.add(0);
//         assertEquals(matches, test.findIntersection(testList, "absolute java"));

//         matches.clear();
//         matches.add(2);
//         matches.add(3);
//         assertEquals(matches, test.findIntersection(testList, "programming" ));

//     }

//     @Test public void testFindID(){
//         SearchTest test = new SearchTest();
//         assertFalse(test.findID(testList, "000001"));
//         assertTrue(test.findID(testList, "000025"));
//     }

//     @Test public void testSearchYear(){
//         SearchTest test = new SearchTest();

//         assertTrue(test.searchYear(testList, "2013"));
//         assertTrue(test.searchYear(testList, "2013-"));

//         assertTrue(test.searchYear(testList, "- 2015"));
//         assertFalse(test.searchYear(testList, "-1999 "));

//         assertTrue(test.searchYear(testList, "2000-2015"));
//         assertTrue(test.searchYear(testList, "2001 - 2015"));
//         assertFalse(test.searchYear(testList, "1998- 1999"));
//     }



//     @Test public void testSearchDes(){
//         SearchTest test = new SearchTest();
        
//         assertTrue(test.searchDescription(testList, "programming", "", ""));
//         assertTrue(test.searchDescription(testList, "java", "000025", ""));
//         assertTrue(test.searchDescription(testList, "intel", "", "-2015" ));
//         assertTrue(test.searchDescription(testList, "intel", "000107", "2000-2015"));


//         assertFalse(test.searchDescription(testList, "java", "", "2019"));
//         assertFalse(test.searchDescription(testList, "intel", "000025", ""));
//         assertFalse(test.searchDescription(testList, "java", "000025", "2019"));

//     }
// }

