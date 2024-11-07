const errors = document.querySelectorAll('.feedback-error');

const isEmpty = (text) => {
	return text === null || text.length === 0;
}

setTimeout(() => {
	for(let error of errors){
		if(!isEmpty(error.textContent)){
			error.remove();
		}
	}
},2000);

