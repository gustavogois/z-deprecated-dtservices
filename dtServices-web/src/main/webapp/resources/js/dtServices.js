function strPad(i,l,s) {
	var o = i.toString();
	if (!s) { 
		s = '0'; 
	}
	while (o.length < l) {
		o = s + o;
	}
	return o;
};

function geralNextFocus() {
	$('.geralNextFocus[type=text]').keydown(function(e) {
		if (e.which === 13) {
			var fields = $('.geralNextFocus[type=text]');
			var index = fields.index(this) + 1;
			fields.eq(index).focus();
			fields.eq(index).select();
			return false;
		}
	});
}

onBlurMilhar = function( field ){
	value = $( field ).val();
	$( field ).val( strPad( value, 4 ) );
	tdGrupo = $( field ).parent().next();
	if( tdGrupo.hasClass( "grupo" ) ){
		premioDezena = ( value % 100 );
		grupo = Math.floor( premioDezena / 4 ) + ( premioDezena % 4 == 0? 0: 1 ); 
		tdGrupo.attr( "class", "grupo grupo_" + grupo );
	}
}

function isNumber(event) {
	if (event) {
		var charCode = (event.which) ? event.which : event.keyCode;
		if ((charCode < 48 || charCode > 57) 
				&& (charCode < 96 || charCode > 105)
				&& charCode != 8 && charCode != 46 && charCode != 9 && charCode != 13){
			return false;
		}
	}
	return true;
}

function jogoControlPalpiteNumber(event){
	if( isNumber(event) ){
		var charCode = (event.which) ? event.which : event.keyCode;
		if( charCode == 8 || charCode == 46 || charCode == 9 || charCode == 13 ){
			return true;
		}
		tipoDeJogo = $( '#tipoDeJogo_input').val();
		switch(tipoDeJogo){
		case '225':
		case '25':
		case '325':
			if( 96 < charCode || charCode < 105 ){
				charCode -= 48;
			}
			var palpite = $('#palpite_input').val() + String.fromCharCode( charCode );
			return palpite == '' || parseInt(palpite) < 26;
		default:
			return true;
		}
	}
	return false;
}
function jogoGetMaxlength(){
	tipoDeJogo = $( '#tipoDeJogo_input').val();
	switch(tipoDeJogo){
	case '4':
	case '43':
		return 4;
	case '430':
	case '30':
	case '40':
		return 9;
	case '3':
		return 3;
	default:
		return 2;
	}
}

function jogoDefineMaxLength(){
	$('#palpite_input').attr('maxlength',jogoGetMaxlength());
	$('#palpite_input').val('');
	palpitesField = $('#palpites ul')
	palpitesField.html('<li class="ui-autocomplete-input-token"><input type="text" id="palpites_input" name="palpites_input" autocomplete="off" tabindex="-1" aria-autocomplete="listbox"></li>');
	$('#palpites_input').val('');
	$('#palpites_hinput').html('')
}

function jogoNextFocus() {
	$('.geralNextFocus input[type!=hidden],input.geralNextFocus').keydown(function(e) {
		var fields = $('.geralNextFocus input[type!=hidden],input.geralNextFocus');
		var index = fields.index(this);
		switch(e.which){
			case 40:
				if( this.id == 'tipoDeJogo_focus' || this.id == 'cambista_focus' ){
					return true;
				}
			case 9:
			case 13:
				if( this.id != 'palpite_input' ){
					index += 1;
				}else{
					return true;
				}
				break;
			case 32:
				return true;
			case 38:
				if( this.id == 'tipoDeJogo_focus' || this.id == 'cambista_focus' ){
					return true;
				}
				index -= 1;
				break;
			case 39:
				$( '#btnActionNovoTipoJogo' ).focus();
				return false;
			default:
				return true;
		}
		fields.eq(index).focus();
		fields.eq(index).select();
		return false;
	});
}

function jogoNextBtn() {
	$('.geralNextBtn').keydown(function(e) {
		var fields = $('.geralNextBtn');
		var index = fields.index(this);
		switch(e.which){
			case 13:
				return true;
			case 40:
				index += 1;
				break;
			case 38:
				index -= 1;
				break;
			case 37:
				$( '#tipoDeJogo_input' ).focus();
				return false;
		}
		fields.eq(index).focus();
		fields.eq(index).select();
		return false;
	});
}

function palpiteAdd() {
	palpite = $('#palpite_input').val();
	if (palpite.trim() != '') {
		padVal = jogoGetMaxlength();
		padVal = padVal == 9? 4: padVal;
		htmlNewPalpite = '<li data-token-value="'
				+ strPad( palpite, padVal )
				+ '" class="ui-autocomplete-token ui-state-active ui-corner-all ui-helper-hidden" style="display: list-item;"><span class="ui-autocomplete-token-icon ui-icon ui-icon-close"></span><span class="ui-autocomplete-token-label">'
				+ strPad( palpite, padVal ) + '</span></li>';
		palpitesField = $('#palpites ul');
		htmlOld = palpitesField.html();
		indexOf = htmlOld.indexOf('<li class="ui-autocomplete-input-token">')

		palpitesField.html(htmlOld.substring(0, indexOf) + htmlNewPalpite
				+ htmlOld.substring(indexOf));

		option = {
			value : palpite,
			text : palpite,
			selected : 'selected'
		};
		$('#palpites_hinput').append($("<option>", option));
		$('#palpite_input').val('')
	}else{
		$( '#colocacaoIni_input' ).focus();
	}
}

function sorteioCalcula6e7Premio() {
	premio6 = 0;
	for (i = 0; 5 > i; i++) {
		premio = $('#resultado\\:' + i + '\\:premio').val();
		premio6 += (premio == '' ? 0 : parseInt(premio));
	}
	if (premio6 > 1000) {
		premio6S = new String(premio6)
		premio6 = premio6S.substring((premio6S.length - 3), premio6S.length);
	}
	$('#resultado\\:5\\:premio').val(premio6);
	$('#resultado\\:5\\:premio_hinput').val(premio6);

	premio1 = $('#resultado\\:0\\:premio').val();
	premio1 = (premio1 == '' ? 0 : parseInt(premio1));

	premio2 = $('#resultado\\:1\\:premio').val();
	premio2 = (premio2 == '' ? 0 : parseInt(premio2));

	premio7 = parseInt((premio2 * premio1) / 1000);
	if (premio7 > 1000) {
		premio7S = new String(premio7);
		premio7 = premio7S.substring(premio7S.length - 3, premio7S.length);
	}
	$('#resultado\\:6\\:premio').val(premio7);
	$('#resultado\\:6\\:premio_hinput').val(premio7);
}