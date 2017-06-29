import de.abas.ws.rest.script.representation.chart.C3Chart
import ratpack.exec.Promise


C3Chart chart = new C3Chart()
chart.addColumn('Value', (1..10).collect { it }, C3Chart.GraphType.BAR)
chart.addColumn('Negative value', (-1..-10).collect { it }, C3Chart.GraphType.BAR)
chart.addColumn('Cosinus', (1..10).collect { Math.cos(it)*10 }, C3Chart.GraphType.AREA_SPLINE)
chart.addColumn('Sinus', (1..10).collect { Math.sin(it)*10 }, C3Chart.GraphType.AREA_SPLINE)

chart.setColors(['#333333', '#555555', '#777777', '#999999', '#BBBBBB'])

chart.group(['Value', 'Negative value'])

return Promise.value(chart.asJSON())