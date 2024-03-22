var selectElement = document.getElementById('selectCombo');
var keywordInput = document.getElementById('searchKeyword');

var urlParams =new URLSearchParams(window.location.search);

var typeParam = urlParams.get('type'); 

if (typeParam){
	selectElement.value = typeParam; 
}else {
	selectElement.getElementsByTagName('option')[0].selected = 'selected';
}


function validateForm() {

    var selectedType = selectElement.value;
    var keyword = keywordInput.value.trim(); 
    

    if (selectedType === "") {
    	Swal.fire({
		    text: "검색조건을 선택해주세요",
		    icon: "warning",
		    showCancelButton: true, 
		    cancelButtonColor: "gray", 
		    cancelButtonText: '닫기',
		    showConfirmButton: false, 
		});
        return false; 
    }
    if (keyword === "") {
    	Swal.fire({
		    text: "검색어를 입력해주세요",
		    icon: "warning",
		    showCancelButton: true, 
		    cancelButtonColor: "gray", 
		    cancelButtonText: '닫기', 
		    showConfirmButton: false,
		});
        return false; 
    }
    return true; 
}
						
						
						