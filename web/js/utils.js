/* 
 *  Este codigo faz parte do primeiro trabalho desenvolvido para o curso 
 *  EEL 418 - Programacao Avancada, ministrado no primeiro semestre
 *  de 2016 pelo professor Jorge Lopes de Souza Leao
 */

function submitForm(formID,buttonValue){
    document.getElementById(formID).buttonId.value=buttonValue;
    document.getElementById(formID).submit();
}

function limparCampos(formID){
    document.getElementById(formID).serialno.value='';
    document.getElementById(formID).titulo.value='';
    document.getElementById(formID).autoria.value='';
}

