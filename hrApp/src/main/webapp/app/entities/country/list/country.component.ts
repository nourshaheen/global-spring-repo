import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICountry } from '../country.model';
import { CountryService } from '../service/country.service';
import { CountryDeleteDialogComponent } from '../delete/country-delete-dialog.component';

@Component({
  selector: 'jhi-country',
  templateUrl: './country.component.html',
})
export class CountryComponent implements OnInit {
  countries?: ICountry[];
  isLoading = false;

  constructor(protected countryService: CountryService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.countryService.query().subscribe({
      next: (res: HttpResponse<ICountry[]>) => {
        this.isLoading = false;
        this.countries = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: ICountry): number {
    return item.id!;
  }

  delete(country: ICountry): void {
    const modalRef = this.modalService.open(CountryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.country = country;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
