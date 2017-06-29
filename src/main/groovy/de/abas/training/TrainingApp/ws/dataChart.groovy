import de.abas.ws.rest.script.representation.chart.C3Chart
/*
	{label: 'Customer', value: '(Customer)'},
	{label: 'Vendor', value: '(Vendor)'},
	{label: 'Employee', value: '(Employee)'},
	{label: 'SubledgerAccountRange', value: '(SubledgerAccountRange)'},
	{label: 'Account', value: '(Account)'},
	{label: 'AccountRange', value: '(AccountRange)'},
	{label: 'ProjectHeader', value: '(ProjectHeader)'}
 */
def vtab = params.vtab ?: '(Vendor)'

def fields = ['ofsaldo', 'auswaehr^iso3a', 'ofsoll', 'ofhaben', 'ftag1', 'fbetr1', 'ftag2', 'fbetr2',
			  'ftag3', 'fbetr3', 'ftag4', 'fbetr4', 'f2tag1', 'f2betr1', 'f2tag2', 'f2betr2', 'f2tag3',
			  'f2betr3', 'f2tag4', 'f2betr4', 'fshn1', 'fshn2', 'fshn3', 'fshn4', 'f2shn1', 'f2shn2',
			  'f2shn3', 'f2shn4', 'ofshn', 'fgesamt']

edpFactory.infosystem ('lop op')
	.set('vtab', vtab)
	.returnHeaderFields( *fields )
	.start()
	.map { infosystem ->

		C3Chart chart = new C3Chart()

		chart.addColumn(infosystem.header.f2tag1, [infosystem.header.f2betr1], C3Chart.GraphType.DONUT)
		chart.addColumn(infosystem.header.f2tag2, [infosystem.header.f2betr2], C3Chart.GraphType.DONUT)
		chart.addColumn(infosystem.header.f2tag3, [infosystem.header.f2betr3], C3Chart.GraphType.DONUT)
		chart.addColumn(infosystem.header.f2tag4, [infosystem.header.f2betr4], C3Chart.GraphType.DONUT)

		chart.c3.donut = [
		    title: 'Total: ' + infosystem.header.fgesamt
		]

		return chart.asJSON()
	}