var ioc={
		dataSource : {
			type : "org.apache.commons.dbcp.BasicDataSource",
			fields : {
				driverClassName : 'com.mysql.jdbc.Driver',
				url : 'jdbc:mysql://115.28.158.206/score',
				username : 'score',
				password : '123456'

			},
			events : {
				depose : "close"
			}

		},
		dao : {
			type : "org.nutz.dao.impl.NutDao",
			args : [ {
				refer : "dataSource"
			} ]

		}
}