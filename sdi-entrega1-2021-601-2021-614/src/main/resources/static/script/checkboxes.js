function checkNumberOfSelectedCheckboxes() {
	var checkboxes = document.getElementsByName("deleteUsersCheckbox");
    var numberOfCheckedItems = 0;
    for(var i = 0; i < checkboxes.length; i++) {  
        if(checkboxes[i].checked) {
        	numberOfCheckedItems++;
        }
    }
    if(numberOfCheckedItems <= 0) {
    	$('#deleteButton').attr("disabled", true);
    } else {
    	$('#deleteButton').attr("disabled", false);
    }
}

function getUserIdsFromSelectedCheckboxes() {
	var checkboxes = document.getElementsByName("deleteUsersCheckbox");
	var userIds = [];
	for(var i = 0; i < checkboxes.length; i++) {  
        if(checkboxes[i].checked) {
        	userIds.push(checkboxes[i].value);
        }
	}
	return userIds;
}