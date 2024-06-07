select current_date as todaydate, current_time as nowtime;

#year, month, day, hour, minute, second
select year(orderdate)
from orders;


select year(current_date), month(current_date), day(current_date);


select * from orders where year(orderDate) < 1997;

select 
	dayofweek(current_date) as dayofweek,
    dayofmonth(current_date) as dayofmonth,
    dayofyear(current_date) as dayofyear;
    
    #formatting dates
    Select
		date_format(current_date, '%D-%M-%Y') as eropeformat,
        date_format(current_date, '%m-%d-%y') as usformat;
        
	select dayname(current_Date)as dayofweek;
    
    #date arithmetic
    #adding time to a date
    #takes 2 arguments, first is time you are starting with, second is
    #how much time do you want to add?
    select date_add(current_date, interval 7 week) as Oneweekfromtoday;
    
    select date_sub(current_date, interval 7 week) as OneWeekAgo;
    
    select datediff('2024-01-01', '2023-12-31') as daydifference;
    
    select orderid, datediff(shippeddate, orderdate) as daystaken
    from orders;
    
    select avg(datediff(shippeddate, orderdate)) as daystaken
    from orders;
    
    select
		now() as currentloacaltime,
        convert_tz( now(), @@session.time_zone, 'UTC');