var list1=[];
var list2=[];

function editAddress()
{
	var placeHolder = ["Full Name", "Street","City","Province","Country","Postal Code","Phone Number"]
	var ul1 = document.getElementById("user-address");
	var items1 = ul1.getElementsByTagName("li");
	var editBtn = document.getElementById("editButton");
	//editBtn.parentNode.removeChild(editBtn);
	editBtn.setAttribute("onclick","");
	editBtn.innerHTML="Update Address";
	

	var cancel = document.createElement("button");
	//editBtn.parentNode.removeChild(editBtn);
	cancel.setAttribute("onclick","cancelUpdate();");
	cancel.setAttribute("id","cancel");
	cancel.setAttribute("class","btn btn-block");
	cancel.innerHTML="Cancel";
	
	editBtn.parentNode.appendChild(cancel);
	
	for (var i = 0; i < items1.length; i++) {
		list1[i]=items1[i].innerHTML;
		var field = document.createElement("input");
		field.type = "text";
		field.value = items1[i].innerHTML;
		items1[i].innerHTML = "";
		items1[i].appendChild(document.createElement('br'));
		field.setAttribute("name","user-address-"+i);
		field.setAttribute("class","form-control");
		field.setAttribute("id","user-address-"+i);
		field.setAttribute("placeholder",placeHolder[i]);
		items1[i].appendChild(field);
		
	}

	var ul2 = document.getElementById("user-billingAddress");
	var items2 = ul2.getElementsByTagName("li");

	for (var i = 0; i < items2.length; i++) {
		list2[i]=items2[i].innerHTML;
		var field = document.createElement("input");
		field.type = "text";
		field.value = items2[i].innerHTML;
		items2[i].innerHTML = "";
		items2[i].appendChild(document.createElement('br'));
		field.setAttribute("name","user-BillingAddress-"+i);
		field.setAttribute("class","form-control");
		field.setAttribute("id","user-address-"+i);
		field.setAttribute("placeholder",placeHolder[i]);
		items2[i].appendChild(field);
		
	}

}

function cancelUpdate(){
	var editBtn = document.getElementById("editButton");
	//editBtn.parentNode.removeChild(editBtn);
	editBtn.setAttribute("onclick","editAddress();");
	editBtn.innerHTML="Edit Information";
	
	var cancel = document.getElementById("cancel");
	cancel.parentNode.removeChild(cancel);
	
	var ul1 = document.getElementById("user-address");
	var items1 = ul1.getElementsByTagName("li");
	for(var i=0;i<list1.length;i++){
		items1[i].innerHTML=list1[i];
	}
	
	var ul2 = document.getElementById("user-billingAddress");
	var items2 = ul2.getElementsByTagName("li");
	for(var i=0;i<list2.length;i++){
		items2[i].innerHTML=list2[i];
	}
}

