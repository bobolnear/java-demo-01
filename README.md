#JAVA-Demo_01
##提纲
###[1].关于Java时区的详解-2015-01-21
#### Java:
		Date local1970 = DateUtils.convertStringToDate("1970-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");  
        System.out.println("当前时区的格林尼治时间的毫秒数:"+local1970.getTime());  
        //TimeZone timeBJ = TimeZone.getTimeZone("PRC");//得到北京时间的时区  
        TimeZone timeBJ = TimeZone.getTimeZone("GMT+8");//得到北京时间的时区   
        String[] strs = timeBJ.getAvailableIDs();  
        for (int i = 0; i < strs.length; i++) {  
            System.out.println("东八区ID:"+strs[i]);  
        }  
        System.out.println(timeBJ.getOffset(local1970.getTime()));  
        //所以如果要获取当地正确的时间  
        TimeZone timeLocal = TimeZone.getTimeZone(getCalendar(local1970).getTimeZone().getID());//得到当前时间的时区   
        long timeZoneMi= timeLocal.getOffset(local1970.getTime());//当前时间要扣减的毫秒数  
        long date1970 = local1970.getTime() + timeZoneMi;  
        System.out.println("1970年格林威治时间的毫秒数:"+date1970);  
详解[here](http://bobolnear.iteye.com/admin/blogs/2177974).
`src/DateUtils.java`
