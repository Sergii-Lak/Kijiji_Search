# Search Kijiji for Montreal region

A script that searches for a word or expression on the site and outputs the result. Currently, there is a search for sections: Real Estate, Buy Sell, Free Stuff, Cars Trucks:
In script this is:

<b>SearchRealEstate</b><br>
<b>SearchBuySell</b><br>
<b>SearchFreeStuff</b><br>
<b>SearchCarsTrucks</b><br>
# ------------------------------------------------

# USAGE:

Easy to use: create an instance of the same class and use the function SearchInTitle(). 
The first parameter is the word or combination of words to search (string), and the second parameter is the number of search pages.
Example:

var Z = SearchCarsTrucks("Toyota Camry", 5)
Z.SearchInTitle()
# ------------------------------------------------

#### Planned functionality:

```php
    add the ability to send results to email       
```
```php
    add the ability to automatically search N times with pauses of N minutes (for example, run the search every 5 minutes; or every 5 minutes 10 times)
```        
```php
    connecting a file or database containing information about ads that have already been found (each subsequent search returns only new ads and adds them to the database or file)
```    
