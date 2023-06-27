import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  searchQuery: string = '';
  //  selectedFilter:string='all';

  @Output() search: EventEmitter<string> = new EventEmitter<string>();
  
  onSearch() {
     this.search.emit(this.searchQuery);
  //   const filter = {

  //     query: this.searchQuery,

  //     filterType: this.selectedFilter

  //   };

  //   this.search.emit(filter);

  
  }
}