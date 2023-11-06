//페이지가 로딩되면서 바로 실행.
fetch('../studentList.do')
	.then(resolve => resolve.json())
	.then(result => {
		console.log(result);
		let tbody = document.querySelector('#list');
		result.forEach(student => {
			tbody.append(makeTr(student));
		})
	})
	.catch(err => console.log('error=> ', err));

	
	// 등록버튼 이벤트
	document.querySelector('#addBtn').addEventListener('click', addCallback);

	// 수정 버튼 이벤트. 서브릿(db변경) =>  화면에 출력되는 정보 변경
	document.querySelector('#modBtn').addEventListener('click', modifyCallback);
	
	
	// callback 함수.
function addCallback(e){  //등록버튼
	// 학생아이디 입력값.
	let sid = document.querySelector('input[name=sid]').value;
	let sname = document.querySelector('input[name=name]').value;
	let pass = document.querySelector('input[name=pass]').value;
	let dept = document.querySelector('select[name=dept]').value;
	let birth = document.querySelector('input[name=birth]').value;
	
	let param = `sid=${sid}&name=${sname}&password=${pass}&dept=${dept}&birthday=${birth}`;
	console.log(param);
	
	//ajax호출. -> 서블릿 실행.
	// fetch('../addStudent.do?'+param) => get 방식
	// post방식 (파라메터표현X,값의제한X,content-Type지정.)
	
	fetch('../addStudent.do?', {
		method: 'post',
		headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
		body: param
	}).then(resolve => resolve.json())
		.then(result => {
			console.log(result);
			if(result.retCode == 'OK'){
				alert('등록성공');
				let tr = makeTr({studentId: sid, studentName: sname, studentDept: dept, studentBirthday: birth});
				document.querySelector('#list').append(tr);
			}
			else{
				alert('등록실패');
			}
		})
		.catch(err => console.log('error:', err));
		
} // end of addCallback.

function modifyCallback(e){ //수정버튼
    let sid = document.querySelector('.modal-body input[id=sid]').value;
    let name = document.querySelector('.modal-body input[id=name]').value;
    let pass = document.querySelector('.modal-body input[id=pass]').value;
    let birth = document.querySelector('.modal-body input[id=birth]').value;
	
	let param = `sid=${sid}&name=${name}&password=${pass}&birthday=${birth}`;
	
	fetch('../editStudent.do?', {
			method: 'post',
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			body: param
		}).then(resolve => resolve.json())
			.then(result => {
				console.log(result);
				if(result.retCode == 'OK'){
					alert('성공');
					//result.vo.studentId;
					let targetTr = //
						document.querySelector('tr[data-sid='+result.vo.studentId+']')
					let newTr = makeTr(result.vo);
					let parentElem = document.querySelector('#list');
					parentElem.replaceChild(newTr, targetTr);
					document.getElementByid('myModal').style.display = 'none';
				}
				else{
					alert('실패');
				}
			})
			.catch(err => console.log('error:', err));

} // end of modifyCallback.

		// tr 생성함수.
function makeTr(obj){
    let showFields = ['studentId', 'studentName','studentDept','studentBirthday'];
	let tr = document.createElement('tr');
	tr.setAttribute('data-sid', obj.studentId);
	tr.addEventListener('dblclick', showModal);
	
	
		for(let prop of showFields){
			
		 let td = document.createElement('td');
		 td.innerHTML = obj[prop];
		 tr.append(td);
	 }
	 // 삭제버튼
	let td = document.createElement('td');
	let btn = document.createElement('button');
	btn.setAttribute('data-sid', obj.studentId);
	btn.innerHTML = '삭제';
	btn.addEventListener('click', function(e){
			//ajax호출. -> 서블릿 실행.
			fetch('../delStudent.do?sid='+obj.studentId)
			.then(resolve => resolve.json())
			.then(result => {
				console.log(result);
				if(result.retCode == 'OK'){
					alert('삭제성공');
					tr.remove();
				}
				else{
					alert('삭제실패');
				}
			})
			.catch(err => console.log('error: ',err));
	})
	td.append(btn);
	tr.append(td);
	 
	 
	return tr;
	}
	
// 모달 보여주기
function showModal(e){
	let id = this.children[0].innerHTML;
	console.log(e.target.parentElement, this);
    // let id = this.datset.sid; --> makeTr(obj) { tr.setAttribute('data-sid', obj.studentId) }
    // console.log(id);
    
    // Get the modal
    var modal = document.getElementById("myModal");

    modal.style.display = "block";

    fetch("../Getstudent.do?",{
		method: 'post',
		headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
		body: 'id=' + id
	})
    .then(resolve => resolve.json())
    .then(result => {
        console.log(result);
        modal.querySelector('h2').innerHTML = result.studentName;
        modal.querySelector('input[id=sid]').value = result.studentId;
        modal.querySelector('input[id=pass]').value = result.studentPassword;
        modal.querySelector('input[id=name]').value = result.studentName;
        // modal.querySelector('input[name=dept]').value = result.studentDept;
        modal.querySelector('input[id=birth]').value = result.studentBirthday;
    })
    .catch(err => console.log('error: ', err));
		
	
	
	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];
	
	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
	  modal.style.display = "none";
	}
	
	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	  if (event.target == modal) {
	    modal.style.display = "none";
	  }
	}
}
				