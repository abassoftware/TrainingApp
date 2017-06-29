package de.abas.training.TrainingApp.ws

import de.abas.ws.rest.script.representation.table.Table
import de.abas.ws.rest.script.representation.table.Row

def contact = params.contact ?: ''

edpFactory.infosystem('KONTAKT st')
        .set('kontext', contact)
        .set('kunde', "1")
        .set('kundensach', "1")

        .set('interessent', "1")
        .set('intersach', "1")

        .set('lieferant', "1")
        .set('liefersach', "1")

        .set('mitarbeiter', '0')
        .set('adresse', '0')
        .set('bank', '0')
        .returnHeaderFields("kontext^dnr")
        .asProperty("resultType")
        .returnTableFields('tkontakt', 'tkontaktname(OPERATING)', 'ttele', 'temail', 'turlt')
        .asPositionsProperty('tkontakt', 'tkontaktname', 'ttele', 'temail', 'turlt')
        .convertTypes(false)
        .start()
        .map {
            erpData ->
            Table table = new Table()
                    .setTableTitle('Kontakte')
                    .setRowsPerPage(10)
                    .addTextColumn('Typ')
                    .addTextColumn('Name')
                    .addTextColumn('Telefon')
                    .addEmailColumn("Email")
                    .addUrlColumn("Webseite")
            erpData.table.each {
                row ->
                table.addRow(
                    new Row()
                        .addText(row.tkontakt)
                        .addText(row.tkontaktname)
                        .addText(row.ttele)
                        .addEmail(row.temail, row.temail)
                        .addUrl(row.turlt, row.turlt)
                )
            }
            table.asJSON()
}
