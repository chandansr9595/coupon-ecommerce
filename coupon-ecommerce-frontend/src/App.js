import './App.css';
import { Router, Route } from 'react-router-dom';

import HomePage from './components/homePage';
import history from './history';
import AddProduct from './components/addProduct/AddProduct';

function App() {
  return (
    <div className="App">
      <Router history={history}>
        <Route path="/" exact component={HomePage}/>
        <Route path="/createProduct" exact component={AddProduct}/>
      </Router>
    </div>
  );
}

export default App;
