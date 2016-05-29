/* 
 *  Este codigo faz parte do primeiro trabalho desenvolvido para o curso 
 *  EEL 418 - Programacao Avancada, ministrado no primeiro semestre
 *  de 2016 pelo professor Jorge Lopes de Souza Leao
 */

function submitForm(formID,buttonValue){
    document.getElementById(formID).clickedButton.value=buttonValue;
    document.getElementById(formID).submit();
}

function limparCampos(formID){
    document.getElementById(formID).titulo.value='';
    document.getElementById(formID).autoria.value='';
}

function alertMsg(alert_div){
    var msg = document.getElementById(alert_div).getAttribute("msg");
    if (msg !== "null") {
            alert(msg);
    }
}

function resetMsg(alert_div){
    var msg = document.getElementById(alert_div).getAttribute("msg");
    if (msg !== "null") {
            window.location.href="./controller?clickedButton=busca";
    }
}

function reportError(){
    alert("ERRO - Esta referencia não existe");
    window.location.href="./index.jsp";
}

                    

