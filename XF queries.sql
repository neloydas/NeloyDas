/* Select countries where a total number of inhabitants (population) in all cities is greater than 400. */
SELECT Name, CountryID 
FROM XF.Country cnty
WHERE (SELECT SUM(population) 
	FROM XF.City ct
	WHERE cnty.CountryID = ct.CountryID )> 400;

------------------------------------------------------------------------
/* Select names of the countries that have no buildings at all. */
SELECT name
FROM Country cnty
WHERE CountryID IN (SELECT CountryID
        FROM XF.City cty
        LEFT OUTER JOIN XF.Building bd ON cty.CityID = bd.CityID
        WHERE bd.BuildingID IS NULL);
--------------------------------------------------------------------------