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
	.catch(err => console.log('error=>', err))
	
	// 등록버튼 이벤트
	document.querySelector('#addBtn').addEventListener('click', addCallback);

		
		// callback 함수.
function addCallback(e){
	// 학생아이디 입력값.
	let sid = document.querySelector('input[name=sid]').value;
	let sname = document.querySelector('input[name=sname]').value;
	let spass = document.querySelector('input[name=spass]').value;
	let sdept = document.querySelector('select[name=sdept]').value;
	let sbirth = document.querySelector('input[name=sbirth]').value;
	
	let param = `sid=${sid}&name=${sname}&password=${spass}&dept=${sdept}&birthday=${sbirth}`;
	console.log(param);
	//ajax호출. -> 서블릿 실행.
	// fetch('../addStudent.do?'+param) => get 방식
	// post방식 (파라메터표현X,값의제한X,content-Type지정.)
	fetch('../addStudent.do?'+param)
//				fetch('../addStudent.do', {
					
//					method: 'post',
//					headers: { 'Content-Type': 'application/x-www.form-urlencoded' },
//					body: param
//				})
	.then(resolve => resolve.json())
		.then(result => {
			console.log(result);
			if(result.retCode == 'OK'){
				alert('등록성공');
				let tr = makeTr({studentId: sid, studentName: sname, studentDept: sdept, studentBirthday: sbirth});
				document.querySelector('#list').append(tr);
			}
			else{
				alert('등록실패');
			}
		})
		.catch(err => console.log('error:', err));
}
		
		// tr 생성함수.
function makeTr(obj){
	let tr = document.createElement('tr');
	tr.addEventListener('dblclick', showModal);
    let showFields = ['studentId', 'studentName','studentDept','studentBirthday'];

	
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
function showModal(){
	console.log(e.target.parentElement, this);
	let id = this.children[0].innerHTML;
	console.log(id);
	
	// Get the modal
	var modal = document.getElementById("myModal");
	modal.style.display = "block";
	let data = { id: "std1", name: "홍길동", pass: "1234", birth: "1999-09-09"};
	
	modal.querySelector('h2').innerHTML = data.name;
	modal.querySelector('input[name=pass]').value = data.pass;
	modal.querySelector('input[name=name]').value = data.name;
	modal.querySelector('input[name=birth]').value = data.birth;
	
	
	// Get the button that opens the modal
	var btn = document.getElementById("myBtn");
	
	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];
	
	// When the user clicks the button, open the modal 
	btn.onclick = function() {
	  modal.style.display = "block";
	}
	
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
				