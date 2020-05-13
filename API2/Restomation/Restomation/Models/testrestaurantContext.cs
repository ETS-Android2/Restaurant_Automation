using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace Restomation.Models
{
    public partial class testrestaurantContext : DbContext
    {

        public testrestaurantContext(DbContextOptions<testrestaurantContext> options)
            : base(options)
        {
        }

        public virtual DbSet<Bills> Bills { get; set; }
        public virtual DbSet<Cart> Cart { get; set; }
        public virtual DbSet<MenuCategory> MenuCategory { get; set; }
        public virtual DbSet<MenuItems> MenuItems { get; set; }
        public virtual DbSet<OrderDetails> OrderDetails { get; set; }
        public virtual DbSet<OrderFlow> OrderFlow { get; set; }
        public virtual DbSet<OrderHistory> OrderHistory { get; set; }
        public virtual DbSet<OrderStatus> OrderStatus { get; set; }
        public virtual DbSet<Orders> Orders { get; set; }
        public virtual DbSet<Reservation> Reservation { get; set; }
        public virtual DbSet<ReservationDetails> ReservationDetails { get; set; }
        public virtual DbSet<Tables> Tables { get; set; }
        public virtual DbSet<Tokens> Tokens { get; set; }
        public virtual DbSet<UserType> UserType { get; set; }
        public virtual DbSet<Users> Users { get; set; }
        public virtual DbSet<SPGetMenuItems> SPGetMenuItems { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<SPGetMenuItems>(entity =>
            {
                entity.HasKey(e => e.ItemId)
                    .HasName("PK__MenuItem__56A128AA4EAFB712");

                entity.Property(e => e.ItemId).HasColumnName("itemId");

                entity.Property(e => e.AvailableQty).HasColumnName("availableQty");

                entity.Property(e => e.CategoryTitle).HasColumnName("categoryTitle");

                entity.Property(e => e.CreatedBy).HasColumnName("createdBy");

                entity.Property(e => e.DeletedBy).HasColumnName("deletedBy");

                entity.Property(e => e.ItemDescription)
                    .HasColumnName("itemDescription")
                    .HasMaxLength(150)
                    .IsUnicode(false);

                entity.Property(e => e.ItemName)
                    .IsRequired()
                    .HasColumnName("itemName")
                    .HasMaxLength(30)
                    .IsUnicode(false);

                entity.Property(e => e.ItemStatusTitle).HasColumnName("itemStatusTitle");

                entity.Property(e => e.Price)
                    .HasColumnName("price")
                    .HasColumnType("decimal(4, 2)");

                entity.Property(e => e.UpdatedBy).HasColumnName("updatedBy");

                
            });

            modelBuilder.Entity<Bills>(entity =>
            {
                entity.HasNoKey();

                entity.Property(e => e.BillDate)
                    .HasColumnName("billDate")
                    .HasColumnType("datetime");

                entity.Property(e => e.BillId).HasColumnName("billId");

                entity.Property(e => e.BillingAmount)
                    .HasColumnName("billingAmount")
                    .HasColumnType("decimal(6, 2)");

                entity.Property(e => e.IsCardPayment).HasColumnName("isCardPayment");

                entity.Property(e => e.OrderId).HasColumnName("orderId");

                entity.HasOne(d => d.Order)
                    .WithMany()
                    .HasForeignKey(d => d.OrderId)
                    .HasConstraintName("FK__Bills__orderId__6A30C649");
            });

            modelBuilder.Entity<Cart>(entity =>
            {
                entity.Property(e => e.CartId).HasColumnName("cartId");

                entity.Property(e => e.AddedBy).HasColumnName("addedBy");

                entity.Property(e => e.IsOrdered).HasColumnName("isOrdered");

                entity.Property(e => e.MenuItemId).HasColumnName("menuItemId");

                entity.Property(e => e.Quantity).HasColumnName("quantity");

                entity.HasOne(d => d.AddedByNavigation)
                    .WithMany(p => p.Cart)
                    .HasForeignKey(d => d.AddedBy)
                    .HasConstraintName("FK__Cart__addedBy__5629CD9C");

                entity.HasOne(d => d.MenuItem)
                    .WithMany(p => p.Cart)
                    .HasForeignKey(d => d.MenuItemId)
                    .HasConstraintName("FK__Cart__menuItemId__5535A963");
            });

            modelBuilder.Entity<MenuCategory>(entity =>
            {
                entity.HasKey(e => e.CategoryId)
                    .HasName("PK__MenuCate__23CAF1F81AD1EAA2");

                entity.HasIndex(e => e.CategoryTitle)
                    .HasName("UQ__MenuCate__ECE1B9EB3849D092")
                    .IsUnique();

                entity.Property(e => e.CategoryId).HasColumnName("categoryID");

                entity.Property(e => e.CategoryTitle)
                    .IsRequired()
                    .HasColumnName("categoryTitle")
                    .HasMaxLength(30)
                    .IsUnicode(false);
            });

            modelBuilder.Entity<MenuItems>(entity =>
            {
                entity.HasKey(e => e.ItemId)
                    .HasName("PK__MenuItem__56A128AA4EAFB712");

                entity.Property(e => e.ItemId).HasColumnName("itemId");

                entity.Property(e => e.AvailableQty).HasColumnName("availableQty");

                entity.Property(e => e.CategoryId).HasColumnName("categoryID");

                entity.Property(e => e.CreatedBy).HasColumnName("createdBy");

                entity.Property(e => e.DeletedBy).HasColumnName("deletedBy");

                entity.Property(e => e.ItemDescription)
                    .HasColumnName("itemDescription")
                    .HasMaxLength(150)
                    .IsUnicode(false);

                entity.Property(e => e.ItemName)
                    .IsRequired()
                    .HasColumnName("itemName")
                    .HasMaxLength(30)
                    .IsUnicode(false);

                entity.Property(e => e.ItemStatusTitle).HasColumnName("itemStatusTitle");

                entity.Property(e => e.Price)
                    .HasColumnName("price")
                    .HasColumnType("decimal(4, 2)");

                entity.Property(e => e.UpdatedBy).HasColumnName("updatedBy");

                entity.HasOne(d => d.Category)
                    .WithMany(p => p.MenuItems)
                    .HasForeignKey(d => d.CategoryId)
                    .HasConstraintName("FK__MenuItems__categ__46E78A0C");

                entity.HasOne(d => d.CreatedByNavigation)
                    .WithMany(p => p.MenuItemsCreatedByNavigation)
                    .HasForeignKey(d => d.CreatedBy)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__MenuItems__creat__47DBAE45");

                entity.HasOne(d => d.DeletedByNavigation)
                    .WithMany(p => p.MenuItemsDeletedByNavigation)
                    .HasForeignKey(d => d.DeletedBy)
                    .HasConstraintName("FK__MenuItems__delet__49C3F6B7");

                entity.HasOne(d => d.UpdatedByNavigation)
                    .WithMany(p => p.MenuItemsUpdatedByNavigation)
                    .HasForeignKey(d => d.UpdatedBy)
                    .HasConstraintName("FK__MenuItems__updat__48CFD27E");
            });

            modelBuilder.Entity<OrderDetails>(entity =>
            {
                entity.HasKey(e => new { e.OrderId, e.ItemId })
                    .HasName("PK__OrderDet__BD6321D73BCBC5BF");

                entity.Property(e => e.OrderId).HasColumnName("orderId");

                entity.Property(e => e.ItemId).HasColumnName("itemId");

                entity.Property(e => e.ItemQty).HasColumnName("itemQty");

                entity.HasOne(d => d.Item)
                    .WithMany(p => p.OrderDetails)
                    .HasForeignKey(d => d.ItemId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__OrderDeta__itemI__68487DD7");

                entity.HasOne(d => d.Order)
                    .WithMany(p => p.OrderDetails)
                    .HasForeignKey(d => d.OrderId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__OrderDeta__order__6754599E");
            });

            modelBuilder.Entity<OrderFlow>(entity =>
            {
                entity.Property(e => e.OrderFlowId).HasColumnName("orderFlowId");

                entity.Property(e => e.CurrentStatus).HasColumnName("currentStatus");

                entity.Property(e => e.NextStatus).HasColumnName("nextStatus");

                entity.HasOne(d => d.CurrentStatusNavigation)
                    .WithMany(p => p.OrderFlowCurrentStatusNavigation)
                    .HasForeignKey(d => d.CurrentStatus)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__OrderFlow__curre__5AEE82B9");

                entity.HasOne(d => d.NextStatusNavigation)
                    .WithMany(p => p.OrderFlowNextStatusNavigation)
                    .HasForeignKey(d => d.NextStatus)
                    .HasConstraintName("FK__OrderFlow__nextS__5BE2A6F2");
            });

            modelBuilder.Entity<OrderHistory>(entity =>
            {
                entity.HasKey(e => new { e.OrderId, e.StatusId })
                    .HasName("PK__OrderHis__9B6B64FCC3DAD31D");

                entity.Property(e => e.OrderId).HasColumnName("orderId");

                entity.Property(e => e.StatusId).HasColumnName("statusId");

                entity.Property(e => e.CreatedTime)
                    .HasColumnName("createdTime")
                    .HasColumnType("datetime")
                    .HasDefaultValueSql("(getdate())");

                entity.HasOne(d => d.Order)
                    .WithMany(p => p.OrderHistory)
                    .HasForeignKey(d => d.OrderId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__OrderHist__order__6383C8BA");

                entity.HasOne(d => d.Status)
                    .WithMany(p => p.OrderHistory)
                    .HasForeignKey(d => d.StatusId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__OrderHist__statu__6477ECF3");
            });

            modelBuilder.Entity<OrderStatus>(entity =>
            {
                entity.Property(e => e.OrderStatusId).HasColumnName("orderStatusId");

                entity.Property(e => e.OrderStatusTitle)
                    .IsRequired()
                    .HasColumnName("orderStatusTitle")
                    .HasMaxLength(20)
                    .IsUnicode(false);
            });

            modelBuilder.Entity<Orders>(entity =>
            {
                entity.HasKey(e => e.OrderId)
                    .HasName("PK__Orders__0809335D23C738BF");

                entity.Property(e => e.OrderId).HasColumnName("orderId");

                entity.Property(e => e.IdDiningIn).HasColumnName("idDiningIn");

                entity.Property(e => e.OrderDate)
                    .HasColumnName("orderDate")
                    .HasColumnType("datetime")
                    .HasDefaultValueSql("(getdate())");

                entity.Property(e => e.ReservationId).HasColumnName("reservationId");

                entity.HasOne(d => d.Reservation)
                    .WithMany(p => p.Orders)
                    .HasForeignKey(d => d.ReservationId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__Orders__reservat__5FB337D6");
            });

            modelBuilder.Entity<Reservation>(entity =>
            {
                entity.Property(e => e.ReservationId).HasColumnName("reservationId");

                entity.Property(e => e.EndTime).HasColumnName("endTime");

                entity.Property(e => e.NumberOfPeople).HasColumnName("numberOfPeople");

                entity.Property(e => e.ReservationDate)
                    .HasColumnName("reservationDate")
                    .HasColumnType("datetime");

                entity.Property(e => e.ReservedBy).HasColumnName("reservedBy");

                entity.Property(e => e.StartTime).HasColumnName("startTime");

                entity.HasOne(d => d.ReservedByNavigation)
                    .WithMany(p => p.Reservation)
                    .HasForeignKey(d => d.ReservedBy)
                    .HasConstraintName("FK__Reservati__reser__4E88ABD4");
            });

            modelBuilder.Entity<ReservationDetails>(entity =>
            {
                entity.HasKey(e => new { e.TableId, e.ReservationId })
                    .HasName("PK__Reservat__1F1C12E05513A72C");

                entity.Property(e => e.TableId).HasColumnName("tableID");

                entity.Property(e => e.ReservationId).HasColumnName("reservationID");

                entity.HasOne(d => d.Reservation)
                    .WithMany(p => p.ReservationDetails)
                    .HasForeignKey(d => d.ReservationId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__Reservati__reser__52593CB8");

                entity.HasOne(d => d.Table)
                    .WithMany(p => p.ReservationDetails)
                    .HasForeignKey(d => d.TableId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__Reservati__table__5165187F");
            });

            modelBuilder.Entity<Tables>(entity =>
            {
                entity.HasKey(e => e.TableId)
                    .HasName("PK__Tables__5408ADBAFB46D739");

                entity.Property(e => e.TableId).HasColumnName("tableID");

                entity.Property(e => e.Capacity).HasColumnName("capacity");

                entity.Property(e => e.IsTableAvailable).HasColumnName("isTableAvailable");
            });

            modelBuilder.Entity<Tokens>(entity =>
            {
                entity.ToTable("tokens");

                entity.HasIndex(e => e.Token)
                    .HasName("UQ__tokens__CA90DA7A603F6EFE")
                    .IsUnique();

                entity.Property(e => e.Id)
                    .HasColumnName("id")
                    .ValueGeneratedNever();

                entity.Property(e => e.CreatedDate)
                    .HasColumnName("createdDate")
                    .HasColumnType("datetime")
                    .HasDefaultValueSql("(getdate())");

                entity.Property(e => e.ExpireDate)
                    .HasColumnName("expireDate")
                    .HasColumnType("datetime");

                entity.Property(e => e.Token).HasColumnName("token");

                entity.HasOne(d => d.IdNavigation)
                    .WithOne(p => p.Tokens)
                    .HasForeignKey<Tokens>(d => d.Id)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__tokens__id__412EB0B6");
            });

            modelBuilder.Entity<UserType>(entity =>
            {
                entity.HasIndex(e => e.UserType1)
                    .HasName("UQ__UserType__73837899BC7B23CA")
                    .IsUnique();

                entity.Property(e => e.UserTypeId)
                    .HasColumnName("userTypeID")
                    .ValueGeneratedNever();

                entity.Property(e => e.UserType1)
                    .IsRequired()
                    .HasColumnName("userType")
                    .HasMaxLength(20)
                    .IsUnicode(false);
            });

            modelBuilder.Entity<Users>(entity =>
            {
                entity.HasKey(e => e.UserId)
                    .HasName("PK__Users__CB9A1CFF7767DAA5");

                entity.HasIndex(e => e.Email)
                    .HasName("UQ__Users__AB6E6164FF9535A7")
                    .IsUnique();

                entity.Property(e => e.UserId).HasColumnName("userId");

                entity.Property(e => e.CreatedDate)
                    .HasColumnName("createdDate")
                    .HasColumnType("datetime")
                    .HasDefaultValueSql("(getdate())");

                entity.Property(e => e.DeletedDate)
                    .HasColumnName("deletedDate")
                    .HasColumnType("datetime");

                entity.Property(e => e.Email)
                    .IsRequired()
                    .HasColumnName("email")
                    .HasMaxLength(150)
                    .IsUnicode(false);

                entity.Property(e => e.FirstName)
                    .IsRequired()
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.Property(e => e.Gender)
                    .HasColumnName("gender")
                    .HasMaxLength(6)
                    .IsUnicode(false);

                entity.Property(e => e.LastName)
                    .HasColumnName("lastName")
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.Property(e => e.Password)
                    .IsRequired()
                    .HasColumnName("password")
                    .HasMaxLength(64)
                    .IsUnicode(false);

                entity.Property(e => e.PhoneNo)
                    .IsRequired()
                    .HasColumnName("phoneNo")
                    .HasMaxLength(10)
                    .IsUnicode(false);

                entity.Property(e => e.ProfileImage)
                    .HasColumnName("profileImage")
                    .HasMaxLength(1000)
                    .IsUnicode(false);

                entity.Property(e => e.UpdatedDate)
                    .HasColumnName("updatedDate")
                    .HasColumnType("datetime");

                entity.Property(e => e.UserTypeId).HasColumnName("userTypeId");

                entity.HasOne(d => d.UserType)
                    .WithMany(p => p.Users)
                    .HasForeignKey(d => d.UserTypeId)
                    .HasConstraintName("FK__Users__userTypeI__3C69FB99");
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);


        public async Task<List<SPGetMenuItems>> GetMenuItemsByCategoryAsync()
        {
            // Initialization.  
            List<SPGetMenuItems> lst = new List<SPGetMenuItems>();

            try
            {
                // Processing.  
                string sqlQuery = "EXEC [dbo].[getMenuItems] ";

                lst = await this.Set<SPGetMenuItems>().FromSqlRaw(sqlQuery).ToListAsync();
            }
            catch (Exception ex)
            {
                throw ex;
            }

            // Info.  
            return lst;
        }
    }
}
